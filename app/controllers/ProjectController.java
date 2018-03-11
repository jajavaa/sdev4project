package controllers;

import io.ebean.DataIntegrityException;
import models.Project;
import models.users.Employee;
import models.users.User;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import views.html.addProject;
import views.html.addProjectEmployees;
import views.html.projects;
import views.html.project;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectController extends Controller{

    private FormFactory formFactory;

    @Inject
    public ProjectController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    @Security.Authenticated(Secured.class)
    public Result projects() {
        List<Project> projectList = Project.getAll();
        User user = User.getWithEmail(session().get("email"));
        if(user.getClass().equals(Employee.class)) {
            List<Project> participating = new ArrayList<>();
            for(Project p: projectList) {
                if(p.getEmployees().contains(user)) {
                    participating.add(p);
                }
            }
            return ok(projects.render(participating, user));
        }
        return ok(projects.render(Project.getAll(), user));
    }

    @Security.Authenticated(Secured.class)
    public Result project(String id) {
        Project proj = Project.get(id);
        if (User.getWithEmail(session().get("email")) instanceof Employee)  {
            if (proj.getEmployees().contains(User.getWithEmail(session().get("email")))) {
                return ok(project.render(proj, User.getWithEmail(session().get("email"))));
            } else return forbidden();
        } else {
            return ok(project.render(proj, User.getWithEmail(session().get("email"))));
        }
    }

    @Security.Authenticated(Secured.class)
    @With(Auth.AuthAdmin.class)
    @Transactional
    public Result create() {
        Form<Project> form = formFactory.form(Project.class);
        return ok(addProject.render(form, null, Employee.getAll(), User.getWithEmail(session().get("email"))));
    }

    @Security.Authenticated(Secured.class)
    @With(Auth.AuthAdmin.class)
    @Transactional
    public Result update(String id) {
        Project project;
        Form<Project> form;
        try {
            project = Project.getFinder().byId(id);
            if(project == null) {
                return badRequest();
            }
            form = formFactory.form(Project.class).fill(project);
        } catch (Exception ex) {
            return badRequest("<h1>400 Bad Request</h1>");
        }
        return ok(addProject.render(form, project, Employee.getAll(),User.getWithEmail(session().get("email"))));
    }

    @Security.Authenticated(Secured.class)
    @With(Auth.AuthAdmin.class)
    public Result delete(String id) {
        Project.getFinder().ref(id).delete();
        flash("success", "Customer has been deleted");
        return redirect(routes.ProjectController.projects());
    }

    @Security.Authenticated(Secured.class)
    @With(Auth.AuthAdmin.class)
    @Transactional
    public Result form() {
        Form<Project> form = formFactory.form(Project.class).bindFromRequest();
        Project project = form.get();
        for(Employee employee: Employee.getAll()) {
            if(form.field(employee.getId()).getValue().isPresent()) {
                project.getEmployees().add(employee);
            }
        }
        try {
            if (!form.hasErrors()) {
                if (project.getId() == null) {
                    return badRequest();
                } else if (project.getId().equals("")) {
                    project.setId(UUID.randomUUID().toString());
                    project.save();
                } else if (!project.getId().equals("")) {
                    project.update();
                }
                return redirect(controllers.routes.ProjectController.projects());
            }
        } catch (DataIntegrityException e) {
            return ok(addProject.render(form, project, Employee.getAll(), User.getWithEmail(session().get("email"))));
        }
        return badRequest(addProject.render(form, project, Employee.getAll(), User.getWithEmail(session().get("email"))));
    }
}
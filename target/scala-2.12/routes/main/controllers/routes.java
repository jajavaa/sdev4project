
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/konrad/Documents/sdev4/project/conf/routes
// @DATE:Sun Mar 11 14:19:22 GMT 2018

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseProjectController ProjectController = new controllers.ReverseProjectController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseDepartmentController DepartmentController = new controllers.ReverseDepartmentController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseLoginController LoginController = new controllers.ReverseLoginController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseEmployeeController EmployeeController = new controllers.ReverseEmployeeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAddressController AddressController = new controllers.ReverseAddressController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseProjectController ProjectController = new controllers.javascript.ReverseProjectController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseDepartmentController DepartmentController = new controllers.javascript.ReverseDepartmentController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseLoginController LoginController = new controllers.javascript.ReverseLoginController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseEmployeeController EmployeeController = new controllers.javascript.ReverseEmployeeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAddressController AddressController = new controllers.javascript.ReverseAddressController(RoutesPrefix.byNamePrefix());
  }

}

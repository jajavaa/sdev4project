
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/konrad/Documents/sdev4/project/conf/routes
// @DATE:Sun Mar 11 14:19:22 GMT 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}

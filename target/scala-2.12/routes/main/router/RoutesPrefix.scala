
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/konrad/code/sdev4project/conf/routes
// @DATE:Fri Mar 23 12:37:19 GMT 2018


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

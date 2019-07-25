/** 
 * Performs mapping for all routers.
 */
public static void mapping(){
  for (  final Router router : routers) {
    final ContextHandlerMeta contextHandlerMeta=router.toContextHandlerMeta();
    RouteHandler.addContextHandlerMeta(contextHandlerMeta);
  }
}

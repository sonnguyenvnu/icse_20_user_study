@Override public HttpServer buildHttpServer(Options options,AdminRequestHandler adminRequestHandler,StubRequestHandler stubRequestHandler){
  try {
    return SERVER_CONSTRUCTOR.newInstance(options,adminRequestHandler,stubRequestHandler);
  }
 catch (  InstantiationException|IllegalAccessException|InvocationTargetException e) {
    return Exceptions.throwUnchecked(e,HttpServer.class);
  }
}

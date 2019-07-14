private static void setGZippableMethods(HandlerWrapper gzipHandler,Class<?> gzipHandlerClass){
  try {
    Method addIncludedMethods=gzipHandlerClass.getDeclaredMethod("addIncludedMethods",String[].class);
    addIncludedMethods.invoke(gzipHandler,new Object[]{GZIPPABLE_METHODS});
  }
 catch (  Exception ignored) {
  }
}

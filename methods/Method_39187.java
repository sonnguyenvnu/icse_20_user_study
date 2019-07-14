public void inject(final ServletContext servletContext,final Targets targets){
  targets.forEachTargetAndIn(madvocScope,(target,in) -> {
    final Class inType=in.type();
    Object value=null;
    if (inType == ServletContext.class) {
      value=servletContext;
    }
    if (value != null) {
      target.writeValue(in,value,true);
    }
  }
);
}

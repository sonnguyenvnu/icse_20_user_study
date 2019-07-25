@Override public void call(Injector injector){
  Grapher grapher=injector.getInstance(Grapher.class);
  try {
    text=grapher.graph();
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}

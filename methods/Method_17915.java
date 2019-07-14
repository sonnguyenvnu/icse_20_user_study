void mount(ComponentContext c,Object convertContent){
  c.enterNoStateUpdatesMethod("mount");
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    ComponentsSystrace.beginSection("onMount:" + ((Component)this).getSimpleName());
  }
  try {
    onMount(c,convertContent);
  }
 catch (  Exception e) {
    c.exitNoStateUpdatesMethod();
    dispatchErrorEvent(c,e);
  }
 finally {
    if (isTracing) {
      ComponentsSystrace.endSection();
    }
  }
  c.exitNoStateUpdatesMethod();
}

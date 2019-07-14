private void vmClassPrepareEvent(ClassPrepareEvent ce){
  ReferenceType rt=ce.referenceType();
  currentThread=ce.thread();
  paused=true;
  if (rt.name().equals(mainClassName)) {
    mainClass=rt;
    classes.add(rt);
    log("main class load: " + rt.name());
    started=true;
  }
 else {
    classes.add(rt);
    log("class load: {0}" + rt.name());
  }
  for (  ClassLoadListener listener : classLoadListeners) {
    if (listener != null) {
      listener.classLoaded(rt);
    }
  }
  paused=false;
  runtime.vm().resume();
}

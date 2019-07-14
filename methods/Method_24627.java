private void vmStartEvent(){
  log("requesting event on main class load: " + mainClassName);
  createClassPrepareRequest(mainClassName);
  createClassPrepareRequest(mainClassName + "$*");
  for (  SketchCode tab : editor.getSketch().getCode()) {
    if (tab.isExtension("java")) {
      log("requesting event on class load: " + tab.getPrettyName());
      String name=tab.getPrettyName();
      createClassPrepareRequest(name);
      createClassPrepareRequest(name + "$*");
    }
  }
  runtime.vm().resume();
}

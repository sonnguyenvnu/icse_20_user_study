private void smoothWarning(String method){
  final String where=external ? "setup" : "settings";
  PGraphics.showWarning("%s() can only be used inside %s()",method,where);
  if (external) {
    PGraphics.showWarning("When run from the PDE, %s() is automatically moved from setup() to settings()",method);
  }
}

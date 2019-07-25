public void init(){
  Display.initDisplay();
  Graphics3D.initGraphics3D();
  File cacheDir=ContextHolder.getCacheDir();
  if (cacheDir != null && cacheDir.exists()) {
    for (    File temp : cacheDir.listFiles()) {
      temp.delete();
    }
  }
}

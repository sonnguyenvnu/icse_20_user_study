public static int detectSystemDPI(){
  try {
    ExtUser32.INSTANCE.SetProcessDpiAwareness(ExtUser32.DPI_AWARENESS_SYSTEM_AWARE);
  }
 catch (  Throwable e) {
  }
  try {
    ExtUser32.INSTANCE.SetThreadDpiAwarenessContext(ExtUser32.DPI_AWARENESS_CONTEXT_SYSTEM_AWARE);
  }
 catch (  Throwable e) {
  }
  try {
    return ExtUser32.INSTANCE.GetDpiForSystem();
  }
 catch (  Throwable e) {
    System.out.println("DPI detection failed, fallback to 96 dpi");
    return -1;
  }
}

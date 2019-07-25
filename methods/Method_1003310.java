@Override public void shutdown(){
  super.shutdown();
  if (statusFrame != null) {
    statusFrame.dispose();
    statusFrame=null;
  }
  if (trayIconUsed) {
    try {
      Utils.callMethod(tray,"remove",trayIcon);
    }
 catch (    Exception e) {
    }
 finally {
      trayIcon=null;
      tray=null;
      trayIconUsed=false;
    }
    System.gc();
    String os=System.getProperty("os.name","generic").toLowerCase(Locale.ENGLISH);
    if (os.contains("mac")) {
      for (      Thread t : Thread.getAllStackTraces().keySet()) {
        if (t.getName().startsWith("AWT-")) {
          t.interrupt();
        }
      }
    }
    Thread.currentThread().interrupt();
  }
}

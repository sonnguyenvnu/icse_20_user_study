private static void processPendingFiles(){
  final MainWindow mainWindow=mainWindowRef.get();
  if (mainWindow != null) {
synchronized (pendingFiles) {
      for (      File f : pendingFiles) {
        mainWindow.getModel().loadFile(f);
      }
      pendingFiles.clear();
    }
  }
}

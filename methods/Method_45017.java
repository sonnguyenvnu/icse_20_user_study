public static void openFileInInstance(File fileToOpen){
synchronized (pendingFiles) {
    if (fileToOpen != null) {
      pendingFiles.add(fileToOpen);
    }
  }
  processPendingFiles();
}

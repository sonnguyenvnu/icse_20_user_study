public static void quitInstance(){
  final MainWindow mainWindow=mainWindowRef.get();
  if (mainWindow != null) {
    mainWindow.onExitMenu();
  }
}

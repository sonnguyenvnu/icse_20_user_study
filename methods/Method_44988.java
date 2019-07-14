public synchronized void initOpenDialog(){
  if (fcOpen == null) {
    fcOpen=createFileChooser("*.jar","*.zip","*.class");
    dirPreferences.retrieveOpenDialogDir(fcOpen);
  }
}

@SuppressWarnings("unchecked") public void openFiles(List<File> files){
  ArrayList<String> errors=new ArrayList<>();
  for (  File file : files) {
    if (file.exists()) {
      FileLoader loader=getFileLoader(file);
      if ((loader != null) && !loader.accept(this,file)) {
        errors.add("Invalid input fileloader: '" + file.getAbsolutePath() + "'");
      }
    }
 else {
      errors.add("File not found: '" + file.getAbsolutePath() + "'");
    }
  }
  if (errors.isEmpty()) {
    for (    File file : files) {
      if (openURI(file.toURI())) {
        configuration.addRecentFile(file);
        mainView.updateRecentFilesMenu(configuration.getRecentFiles());
      }
    }
  }
 else {
    StringBuilder messages=new StringBuilder();
    int index=0;
    for (    String error : errors) {
      if (index > 0) {
        messages.append('\n');
      }
      if (index >= 20) {
        messages.append("...");
        break;
      }
      messages.append(error);
      index++;
    }
    JOptionPane.showMessageDialog(mainView.getMainFrame(),messages.toString(),"Error",JOptionPane.ERROR_MESSAGE);
  }
}

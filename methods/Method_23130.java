static protected void selectImpl(final Frame parentFrame,final String prompt,final File defaultSelection,final Callback callback,final int mode){
  File selectedFile=null;
  if (useNativeSelect) {
    FileDialog dialog=new FileDialog(parentFrame,prompt,mode);
    if (defaultSelection != null) {
      dialog.setDirectory(defaultSelection.getParent());
      dialog.setFile(defaultSelection.getName());
    }
    dialog.setVisible(true);
    String directory=dialog.getDirectory();
    String filename=dialog.getFile();
    if (filename != null) {
      selectedFile=new File(directory,filename);
    }
  }
 else {
    JFileChooser chooser=new JFileChooser();
    chooser.setDialogTitle(prompt);
    if (defaultSelection != null) {
      chooser.setSelectedFile(defaultSelection);
    }
    int result=-1;
    if (mode == FileDialog.SAVE) {
      result=chooser.showSaveDialog(parentFrame);
    }
 else     if (mode == FileDialog.LOAD) {
      result=chooser.showOpenDialog(parentFrame);
    }
    if (result == JFileChooser.APPROVE_OPTION) {
      selectedFile=chooser.getSelectedFile();
    }
  }
  callback.handle(selectedFile);
}

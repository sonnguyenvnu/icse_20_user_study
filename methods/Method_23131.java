/** 
 * See selectInput() for details.
 * @webref input:files
 * @param prompt message to the user
 * @param callback name of the method to be called when the selection is made
 */
static public void selectFolder(final Frame parentFrame,final String prompt,final File defaultSelection,final Callback callback){
  File selectedFile=null;
  if (System.getProperty("os.name").contains("Mac") && useNativeSelect) {
    FileDialog fileDialog=new FileDialog(parentFrame,prompt,FileDialog.LOAD);
    System.setProperty("apple.awt.fileDialogForDirectories","true");
    fileDialog.setVisible(true);
    System.setProperty("apple.awt.fileDialogForDirectories","false");
    String filename=fileDialog.getFile();
    if (filename != null) {
      selectedFile=new File(fileDialog.getDirectory(),fileDialog.getFile());
    }
  }
 else {
    JFileChooser fileChooser=new JFileChooser();
    fileChooser.setDialogTitle(prompt);
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    if (defaultSelection != null) {
      fileChooser.setSelectedFile(defaultSelection);
    }
    int result=fileChooser.showOpenDialog(parentFrame);
    if (result == JFileChooser.APPROVE_OPTION) {
      selectedFile=fileChooser.getSelectedFile();
    }
  }
  callback.handle(selectedFile);
}

static public void selectFolder(final String prompt,final String callbackMethod,final File defaultSelection,final Object callbackObject,final Frame parentFrame,final PApplet sketch){
  EventQueue.invokeLater(new Runnable(){
    public void run(){
      File selectedFile=null;
      boolean hide=(sketch != null) && (sketch.g instanceof PGraphicsOpenGL) && (platform == WINDOWS);
      if (hide)       sketch.surface.setVisible(false);
      if (platform == MACOSX && useNativeSelect != false) {
        FileDialog fileDialog=new FileDialog(parentFrame,prompt,FileDialog.LOAD);
        if (defaultSelection != null) {
          fileDialog.setDirectory(defaultSelection.getAbsolutePath());
        }
        System.setProperty("apple.awt.fileDialogForDirectories","true");
        fileDialog.setVisible(true);
        System.setProperty("apple.awt.fileDialogForDirectories","false");
        String filename=fileDialog.getFile();
        if (filename != null) {
          selectedFile=new File(fileDialog.getDirectory(),fileDialog.getFile());
        }
      }
 else {
        checkLookAndFeel();
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setDialogTitle(prompt);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (defaultSelection != null) {
          fileChooser.setCurrentDirectory(defaultSelection);
        }
        int result=fileChooser.showOpenDialog(parentFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
          selectedFile=fileChooser.getSelectedFile();
        }
      }
      if (hide)       sketch.surface.setVisible(true);
      selectCallback(selectedFile,callbackMethod,callbackObject);
    }
  }
);
}

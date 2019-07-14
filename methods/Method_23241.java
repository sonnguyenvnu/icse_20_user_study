static protected void selectImpl(final String prompt,final String callbackMethod,final File defaultSelection,final Object callbackObject,final Frame parentFrame,final int mode,final PApplet sketch){
  EventQueue.invokeLater(new Runnable(){
    public void run(){
      File selectedFile=null;
      boolean hide=(sketch != null) && (sketch.g instanceof PGraphicsOpenGL) && (platform == WINDOWS);
      if (hide)       sketch.surface.setVisible(false);
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
 else         if (mode == FileDialog.LOAD) {
          result=chooser.showOpenDialog(parentFrame);
        }
        if (result == JFileChooser.APPROVE_OPTION) {
          selectedFile=chooser.getSelectedFile();
        }
      }
      if (hide)       sketch.surface.setVisible(true);
      selectCallback(selectedFile,callbackMethod,callbackObject);
    }
  }
);
}

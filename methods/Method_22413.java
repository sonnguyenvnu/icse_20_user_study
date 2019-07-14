/** 
 * Prompt for a sketch to open, and open it in a new window.
 */
public void handleOpenPrompt(){
  final StringList extensions=new StringList();
  for (  Mode mode : getModeList()) {
    extensions.append(mode.getDefaultExtension());
  }
  final String prompt=Language.text("open");
  if (Preferences.getBoolean("chooser.files.native")) {
    FileDialog openDialog=new FileDialog(activeEditor,prompt,FileDialog.LOAD);
    openDialog.setFilenameFilter(new FilenameFilter(){
      public boolean accept(      File dir,      String name){
        for (        String ext : extensions) {
          if (name.toLowerCase().endsWith("." + ext)) {
            return true;
          }
        }
        return false;
      }
    }
);
    openDialog.setVisible(true);
    String directory=openDialog.getDirectory();
    String filename=openDialog.getFile();
    if (filename != null) {
      File inputFile=new File(directory,filename);
      handleOpen(inputFile.getAbsolutePath());
    }
  }
 else {
    if (openChooser == null) {
      openChooser=new JFileChooser();
    }
    openChooser.setDialogTitle(prompt);
    openChooser.setFileFilter(new javax.swing.filechooser.FileFilter(){
      public boolean accept(      File file){
        if (file.isDirectory()) {
          return true;
        }
        for (        String ext : extensions) {
          if (file.getName().toLowerCase().endsWith("." + ext)) {
            return true;
          }
        }
        return false;
      }
      public String getDescription(){
        return "Processing Sketch";
      }
    }
);
    if (openChooser.showOpenDialog(activeEditor) == JFileChooser.APPROVE_OPTION) {
      handleOpen(openChooser.getSelectedFile().getAbsolutePath());
    }
  }
}

public static void display(){
  final JFileChooser fc=new JFileChooser();
  fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
  fc.setDialogTitle(Messages.getString("ProfileChooser.1"));
  fc.setFileFilter(new ProfileChooserFileFilter());
  int returnVal=fc.showDialog(null,Messages.getString("ProfileChooser.2"));
  if (returnVal == JFileChooser.APPROVE_OPTION) {
    File file=fc.getSelectedFile();
    System.setProperty("ums.profile.path",file.getAbsolutePath());
  }
}

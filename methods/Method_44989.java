private JFileChooser createFileChooser(String... fileFilters){
  JFileChooser fc=new JFileChooser();
  for (  String fileFilter : fileFilters) {
    fc.addChoosableFileFilter(new FileChooserFileFilter(fileFilter));
  }
  fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
  fc.setMultiSelectionEnabled(false);
  return fc;
}

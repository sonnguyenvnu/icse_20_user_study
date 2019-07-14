protected void onOpen(){
  Map<String,FileLoader> loaders=FileLoaderService.getInstance().getMapProviders();
  StringBuilder sb=new StringBuilder();
  ArrayList<String> extensions=new ArrayList<>(loaders.keySet());
  extensions.sort(null);
  for (  String extension : extensions) {
    sb.append("*.").append(extension).append(", ");
  }
  sb.setLength(sb.length() - 2);
  String description=sb.toString();
  String[] array=extensions.toArray(new String[0]);
  JFileChooser chooser=new JFileChooser();
  chooser.removeChoosableFileFilter(chooser.getFileFilter());
  chooser.addChoosableFileFilter(new FileNameExtensionFilter("All files (" + description + ")",array));
  for (  String extension : extensions) {
    FileLoader loader=loaders.get(extension);
    chooser.addChoosableFileFilter(new FileNameExtensionFilter(loader.getDescription(),loader.getExtensions()));
  }
  chooser.setCurrentDirectory(configuration.getRecentLoadDirectory());
  if (chooser.showOpenDialog(mainView.getMainFrame()) == JFileChooser.APPROVE_OPTION) {
    configuration.setRecentLoadDirectory(chooser.getCurrentDirectory());
    openFile(chooser.getSelectedFile());
  }
}

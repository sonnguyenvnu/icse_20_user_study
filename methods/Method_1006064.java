@Override public void execute(){
  List<Path> filesToOpen=new ArrayList<>();
  FileDialogConfiguration fileDialogConfiguration=new FileDialogConfiguration.Builder().addExtensionFilter(StandardFileType.BIBTEX_DB).withDefaultExtension(StandardFileType.BIBTEX_DB).withInitialDirectory(getInitialDirectory()).build();
  List<Path> chosenFiles=dialogService.showFileOpenDialogAndGetMultipleFiles(fileDialogConfiguration);
  filesToOpen.addAll(chosenFiles);
  openFiles(filesToOpen,true);
}

public void browse(){
  FileDialogConfiguration fileDialogConfiguration=new FileDialogConfiguration.Builder().addExtensionFilter(Localization.lang("Custom layout file"),StandardFileType.LAYOUT).withDefaultExtension(Localization.lang("Custom layout file"),StandardFileType.LAYOUT).withInitialDirectory(getExportWorkingDirectory()).build();
  dialogService.showFileOpenDialog(fileDialogConfiguration).ifPresent(f -> layoutFile.set(f.toAbsolutePath().toString()));
}

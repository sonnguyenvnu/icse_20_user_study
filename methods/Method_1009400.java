private synchronized void save(){
  FileTime latestModifiedTime=IOHelper.getLastModifiedTime(getPath());
  if (Objects.nonNull(latestModifiedTime) && Objects.nonNull(getLastModifiedTime())) {
    if (latestModifiedTime.compareTo(getLastModifiedTime()) > 0) {
      this.select();
      ButtonType buttonType=AlertHelper.conflictAlert(getPath()).orElse(ButtonType.CANCEL);
      if (buttonType == ButtonType.CANCEL) {
        return;
      }
      if (buttonType == AlertHelper.LOAD_FILE_SYSTEM_CHANGES) {
        load();
      }
    }
 else {
      if (!isNew() && !isChanged()) {
        return;
      }
    }
  }
  if (Objects.isNull(getPath())) {
    final FileChooser fileChooser=directoryService.newFileChooser(String.format("Save file"));
    fileChooser.getExtensionFilters().addAll(ExtensionFilters.ASCIIDOC);
    fileChooser.getExtensionFilters().addAll(ExtensionFilters.MARKDOWN);
    fileChooser.getExtensionFilters().addAll(ExtensionFilters.ALL);
    File file=fileChooser.showSaveDialog(null);
    if (Objects.isNull(file))     return;
    setPath(file.toPath());
    setTabText(file.toPath().getFileName().toString());
  }
  String editorValue=editorPane.getEditorValue();
  IOHelper.createDirectories(getPath().getParent());
  Optional<Exception> exception=IOHelper.writeToFile(getPath(),editorValue,TRUNCATE_EXISTING,CREATE,SYNC);
  if (exception.isPresent()) {
    return;
  }
  setLastModifiedTime(IOHelper.getLastModifiedTime(getPath()));
  setChangedProperty(false);
  ObservableList<Item> recentFiles=storedConfigBean.getRecentFiles();
  recentFiles.remove(new Item(getPath()));
  recentFiles.add(0,new Item(getPath()));
  directoryService.setInitialDirectory(Optional.ofNullable(getPath().toFile()));
}

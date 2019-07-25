@FXML public void initialize(){
  viewModel=new CustomizeExternalFileTypesViewModel();
  fileTypesTable.setItems(viewModel.getFileTypes());
  fileTypesTableIconColumn.setCellValueFactory(data -> BindingsHelper.constantOf(data.getValue().getIcon()));
  fileTypesTableNameColumn.setCellValueFactory(data -> BindingsHelper.constantOf(data.getValue().getName()));
  fileTypesTableExtensionColumn.setCellValueFactory(data -> BindingsHelper.constantOf(data.getValue().getExtension()));
  fileTypesTableTypeColumn.setCellValueFactory(data -> BindingsHelper.constantOf(data.getValue().getMimeType()));
  fileTypesTableApplicationColumn.setCellValueFactory(data -> BindingsHelper.constantOf(data.getValue().getOpenWithApplication()));
  fileTypesTableEditColumn.setCellValueFactory(data -> BindingsHelper.constantOf(true));
  fileTypesTableDeleteColumn.setCellValueFactory(data -> BindingsHelper.constantOf(true));
  new ValueTableCellFactory<ExternalFileType,JabRefIcon>().withGraphic(JabRefIcon::getGraphicNode).install(fileTypesTableIconColumn);
  new ValueTableCellFactory<ExternalFileType,Boolean>().withGraphic(none -> IconTheme.JabRefIcons.EDIT.getGraphicNode()).withOnMouseClickedEvent((type,none) -> event -> viewModel.edit(type)).install(fileTypesTableEditColumn);
  new ValueTableCellFactory<ExternalFileType,Boolean>().withGraphic(none -> IconTheme.JabRefIcons.REMOVE.getGraphicNode()).withOnMouseClickedEvent((type,none) -> event -> viewModel.remove(type)).install(fileTypesTableDeleteColumn);
}

@FXML public void initialize(){
  viewModel=new ManageProtectedTermsViewModel(termsLoader,dialogService,preferences);
  filesTable.setItems(viewModel.getTermsFiles());
  new ViewModelTableRowFactory<ProtectedTermsList>().withContextMenu(this::createContextMenu).install(filesTable);
  filesTableEnabledColumn.setCellValueFactory(data -> BindingsHelper.constantOf(data.getValue().isEnabled()));
  filesTableEnabledColumn.setCellFactory(CheckBoxTableCell.forTableColumn(filesTableEnabledColumn));
  filesTableDescriptionColumn.setCellValueFactory(data -> BindingsHelper.constantOf(data.getValue().getDescription()));
  filesTableFileColumn.setCellValueFactory(data -> {
    ProtectedTermsList list=data.getValue();
    if (list.isInternalList()) {
      return BindingsHelper.constantOf(Localization.lang("Internal list"));
    }
 else {
      return BindingsHelper.constantOf(data.getValue().getLocation());
    }
  }
);
  filesTableEditColumn.setCellValueFactory(data -> BindingsHelper.constantOf(true));
  filesTableDeleteColumn.setCellValueFactory(data -> BindingsHelper.constantOf(true));
  new ValueTableCellFactory<ProtectedTermsList,Boolean>().withGraphic(none -> IconTheme.JabRefIcons.EDIT.getGraphicNode()).withOnMouseClickedEvent((file,none) -> event -> viewModel.edit(file)).install(filesTableEditColumn);
  new ValueTableCellFactory<ProtectedTermsList,Boolean>().withGraphic(none -> IconTheme.JabRefIcons.REMOVE.getGraphicNode()).withTooltip(none -> Localization.lang("Remove protected terms file")).withOnMouseClickedEvent((file,none) -> event -> viewModel.removeFile(file)).install(filesTableDeleteColumn);
}

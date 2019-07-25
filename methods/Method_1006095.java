@FXML private void initialize() throws NoSuchElementException, WrappedTargetException, UnknownPropertyException {
  viewModel=new ManageCitationsDialogViewModel(ooBase,dialogService);
  citation.setCellValueFactory(cellData -> cellData.getValue().citationProperty());
  new ValueTableCellFactory<CitationEntryViewModel,String>().withGraphic(this::getText).install(citation);
  extraInfo.setCellValueFactory(cellData -> cellData.getValue().extraInformationProperty());
  extraInfo.setEditable(true);
  citationsTableView.setEditable(true);
  citationsTableView.itemsProperty().bindBidirectional(viewModel.citationsProperty());
  extraInfo.setOnEditCommit((  CellEditEvent<CitationEntryViewModel,String> cell) -> {
    cell.getRowValue().setExtraInfo(cell.getNewValue());
  }
);
  extraInfo.setCellFactory(TextFieldTableCell.forTableColumn());
}

private <T>void setupCellValueFactory(JFXTreeTableColumn<Person,T> column,Function<Person,ObservableValue<T>> mapper){
  column.setCellValueFactory((  TreeTableColumn.CellDataFeatures<Person,T> param) -> {
    if (column.validateValue(param)) {
      return mapper.apply(param.getValue().getValue());
    }
 else {
      return column.getComputedValue(param);
    }
  }
);
}

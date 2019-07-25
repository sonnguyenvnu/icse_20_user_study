public void install(TableColumn<S,T> column,StringConverter<T> stringConverter){
  column.setCellFactory(this);
  this.stringConverter=stringConverter;
}

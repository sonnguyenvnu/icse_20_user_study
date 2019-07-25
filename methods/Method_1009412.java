@Override public FXFormNode call(Void param){
  final ListView<Path> listView=spellcheckConfigBean.getLanguagePathList();
  listView.setCellFactory(li -> new TextFieldListCell<>(new StringConverter<Path>(){
    @Override public String toString(    Path object){
      return IOHelper.getPathCleanName(object);
    }
    @Override public Path fromString(    String string){
      return IOHelper.getPath(string);
    }
  }
));
  HBox hBox=new HBox(5);
  hBox.getChildren().add(listView);
  hBox.getChildren().add(new VBox(10,languageLabel,setDefaultButton,addNewLanguageButton));
  HBox.setHgrow(listView,Priority.ALWAYS);
  listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
  setDefaultButton.setOnAction(this::setDefaultLanguage);
  return new FXFormNodeWrapper(hBox,listView.itemsProperty());
}

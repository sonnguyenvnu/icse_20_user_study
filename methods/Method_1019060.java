@Override protected TextField create(ListView<String> view){
  TextField text=new TextField();
  text.setOnAction((e) -> add(text,view));
  return text;
}

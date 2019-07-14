protected void makePrettyName(){
  prettyName=file.getName();
  int dot=prettyName.lastIndexOf('.');
  prettyName=prettyName.substring(0,dot);
}

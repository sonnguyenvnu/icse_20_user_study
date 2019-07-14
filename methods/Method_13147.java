@Override public boolean load(API api,File file){
  api.addPanel(file.getName(),null,"Location: " + file.getAbsolutePath(),new LogPage(api,file.toURI(),TextReader.getText(file)));
  return true;
}

static public void init(Base b){
  base=b;
  file=Base.getSettingsFile(FILENAME);
  mainMenu=new JMenu(Language.text("menu.file.recent"));
  toolbarMenu=new JMenu(Language.text("menu.file.open"));
  try {
    load();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}

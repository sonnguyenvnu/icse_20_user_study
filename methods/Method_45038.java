public void show(String name,String contents){
  OpenFile open=new OpenFile(name,"*/" + name,getTheme(),mainWindow);
  open.setContent(contents);
  hmap.add(open);
  addOrSwitchToTab(open);
}

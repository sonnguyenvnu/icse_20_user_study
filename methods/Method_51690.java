private void init(){
  add(new SimpleNodeSubMenu(model,node));
  addSeparator();
  add(new AttributesSubMenu(model,node));
}

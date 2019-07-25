public String next(){
  String menuItem=(String)items.get(position);
  position=position + 1;
  return menuItem;
}

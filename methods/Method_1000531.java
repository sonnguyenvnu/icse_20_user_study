public void addv(String name,String value){
  if (value == null) {
    items.remove(name);
  }
 else {
    items.addv(name,value);
  }
}

public static ToggleButtonBuilt item(String name){
  ToggleButton item=new ToggleButton();
  item.setText(name);
  return new ToggleButtonBuilt(item);
}

public MenuItemBuilt tip(String tipText){
  Tooltip tooltip=new Tooltip(tipText);
  Tooltip.install(menuItem.getGraphic(),tooltip);
  return this;
}

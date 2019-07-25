public ToggleButtonBuilt tip(String tipText){
  Tooltip tooltip=new Tooltip(tipText);
  Tooltip.install(toggleButton.getGraphic(),tooltip);
  return this;
}

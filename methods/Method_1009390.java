public LabelBuilt tip(String text){
  Tooltip tooltip=new Tooltip(text);
  Tooltip.install(label,tooltip);
  return this;
}

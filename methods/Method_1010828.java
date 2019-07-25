public void apply(Style toStyle){
  Style style=StyleRegistry.getInstance().getStyle(myKey);
  if (style == null) {
    return;
  }
  toStyle.putAll(style,myPriority);
}

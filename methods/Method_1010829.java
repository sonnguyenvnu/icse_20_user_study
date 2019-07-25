public void unapply(Style toStyle){
  Style style=StyleRegistry.getInstance().getStyle(myKey);
  if (style == null) {
    return;
  }
  if (myPriority != 0) {
    Style toRemove=new StyleImpl();
    toRemove.putAll(style,myPriority);
    style=toRemove;
  }
  toStyle.removeAll(style);
}

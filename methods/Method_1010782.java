@NotNull @Override public TransformationMenuContext with(@Nullable SNodeLocation nodeLocation,@Nullable String menuLocation){
  if (nodeLocation == null) {
    nodeLocation=myNodeLocation;
  }
  if (menuLocation == null) {
    menuLocation=myMenuLocation;
  }
  if (myNodeLocation.equals(nodeLocation) && myMenuLocation.equals(menuLocation)) {
    return this;
  }
  return new DefaultTransformationMenuContext(myMenuItemFactory,menuLocation,myEditorContext,nodeLocation,myEditorMenuTrace);
}

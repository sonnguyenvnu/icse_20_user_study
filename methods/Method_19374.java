public void setStickyComponent(ComponentTree component){
  if (component.getLithoView() != null) {
    component.getLithoView().startTemporaryDetach();
  }
  mStickyHeader.setComponentTree(component);
  measureStickyHeader(getWidth());
}

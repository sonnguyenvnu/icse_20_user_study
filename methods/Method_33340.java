@Override protected void handleControlPropertyChanged(String property){
  super.handleControlPropertyChanged(property);
  if ("SELECTED_TAB".equals(property)) {
    isSelectingTab=true;
    selectedTab=getSkinnable().getSelectionModel().getSelectedItem();
    getSkinnable().requestLayout();
  }
 else   if ("WIDTH".equals(property)) {
    clip.setWidth(getSkinnable().getWidth());
  }
 else   if ("HEIGHT".equals(property)) {
    clip.setHeight(getSkinnable().getHeight());
  }
}

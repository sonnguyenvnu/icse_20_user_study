@Override public void setTranslationY(float translationY){
  super.setTranslationY(translationY);
  if (bottomTabContainer.getTag() == null && (delegate == null || !delegate.isSearchOpened())) {
    View parent=(View)getParent();
    if (parent != null) {
      float y=getY() + getMeasuredHeight() - parent.getHeight();
      bottomTabContainer.setTranslationY(-y);
    }
  }
}

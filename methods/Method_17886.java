@Override public void setVisibility(int visibility){
  super.setVisibility(visibility);
  for (int i=0, size=(mDrawableMountItems == null) ? 0 : mDrawableMountItems.size(); i < size; i++) {
    final Drawable drawable=(Drawable)mDrawableMountItems.valueAt(i).getContent();
    drawable.setVisible(visibility == View.VISIBLE,false);
  }
}

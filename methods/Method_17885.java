@Override protected void drawableStateChanged(){
  super.drawableStateChanged();
  for (int i=0, size=(mDrawableMountItems == null) ? 0 : mDrawableMountItems.size(); i < size; i++) {
    final MountItem mountItem=mDrawableMountItems.valueAt(i);
    ComponentHostUtils.maybeSetDrawableState(this,(Drawable)mountItem.getContent(),mountItem.getLayoutFlags(),mountItem.getNodeInfo());
  }
}

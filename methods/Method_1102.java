@Override protected void onBoundsChange(Rect bounds){
  for (int i=0; i < mLayers.length; i++) {
    Drawable drawable=mLayers[i];
    if (drawable != null) {
      drawable.setBounds(bounds);
    }
  }
}

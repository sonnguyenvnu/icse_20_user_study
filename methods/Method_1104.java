@Override protected boolean onLevelChange(int level){
  boolean levelChanged=false;
  for (int i=0; i < mLayers.length; i++) {
    Drawable drawable=mLayers[i];
    if (drawable != null && drawable.setLevel(level)) {
      levelChanged=true;
    }
  }
  return levelChanged;
}

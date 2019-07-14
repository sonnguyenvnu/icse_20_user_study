@Override public void layoutDirection(@Nullable YogaDirection direction){
  mPrivateFlags|=PFLAG_LAYOUT_DIRECTION_IS_SET;
  getOrCreateObjectProps().append(INDEX_LayoutDirection,direction);
}

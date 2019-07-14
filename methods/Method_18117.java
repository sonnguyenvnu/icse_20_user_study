@Override public boolean isLayoutDirectionInherit(){
  return (mPrivateFlags & PFLAG_LAYOUT_DIRECTION_IS_SET) == 0L || getResolvedLayoutDirection() == YogaDirection.INHERIT;
}

@Nullable View getToEpicenterView(@Nullable ArrayMap<String,View> toSharedElements){
  if (enterTransition != null && sharedElementNames.size() > 0 && toSharedElements != null) {
    return toSharedElements.get(sharedElementNames.valueAt(0));
  }
  return null;
}

@Nullable ArrayMap<String,View> captureToSharedElements(@Nullable final View to,boolean isPush){
  if (sharedElementNames.isEmpty() || sharedElementTransition == null || to == null) {
    sharedElementNames.clear();
    return null;
  }
  final ArrayMap<String,View> toSharedElements=new ArrayMap<>();
  TransitionUtils.findNamedViews(toSharedElements,to);
  for (  ViewParentPair removedView : removedViews) {
    toSharedElements.put(removedView.view.getTransitionName(),removedView.view);
  }
  final List<String> names=new ArrayList<>(sharedElementNames.values());
  toSharedElements.retainAll(names);
  if (enterTransitionCallback != null) {
    enterTransitionCallback.onMapSharedElements(names,toSharedElements);
    for (int i=names.size() - 1; i >= 0; i--) {
      String name=names.get(i);
      View view=toSharedElements.get(name);
      if (view == null) {
        String key=findKeyForValue(sharedElementNames,name);
        if (key != null) {
          sharedElementNames.remove(key);
        }
      }
 else       if (!name.equals(view.getTransitionName())) {
        String key=findKeyForValue(sharedElementNames,name);
        if (key != null) {
          sharedElementNames.put(key,view.getTransitionName());
        }
      }
    }
  }
 else {
    for (int i=sharedElementNames.size() - 1; i >= 0; i--) {
      final String targetName=sharedElementNames.valueAt(i);
      if (!toSharedElements.containsKey(targetName)) {
        sharedElementNames.removeAt(i);
      }
    }
  }
  return toSharedElements;
}

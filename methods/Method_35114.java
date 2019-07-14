@Nullable private ArrayMap<String,View> captureFromSharedElements(@NonNull View from){
  if (sharedElementNames.isEmpty() || sharedElementTransition == null) {
    sharedElementNames.clear();
    return null;
  }
  final ArrayMap<String,View> fromSharedElements=new ArrayMap<>();
  TransitionUtils.findNamedViews(fromSharedElements,from);
  final List<String> names=new ArrayList<>(sharedElementNames.keySet());
  fromSharedElements.retainAll(names);
  if (exitTransitionCallback != null) {
    exitTransitionCallback.onMapSharedElements(names,fromSharedElements);
    for (int i=names.size() - 1; i >= 0; i--) {
      String name=names.get(i);
      View view=fromSharedElements.get(name);
      if (view == null) {
        sharedElementNames.remove(name);
      }
 else       if (!name.equals(view.getTransitionName())) {
        String targetValue=sharedElementNames.remove(name);
        sharedElementNames.put(view.getTransitionName(),targetValue);
      }
    }
  }
 else {
    sharedElementNames.retainAll(fromSharedElements.keySet());
  }
  return fromSharedElements;
}

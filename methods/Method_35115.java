void callSharedElementStartEnd(@Nullable ArrayMap<String,View> sharedElements,boolean isStart){
  if (enterTransitionCallback != null) {
    final int count=sharedElements == null ? 0 : sharedElements.size();
    List<View> views=new ArrayList<>(count);
    List<String> names=new ArrayList<>(count);
    for (int i=0; i < count; i++) {
      names.add(sharedElements.keyAt(i));
      views.add(sharedElements.valueAt(i));
    }
    if (isStart) {
      enterTransitionCallback.onSharedElementStart(names,views,null);
    }
 else {
      enterTransitionCallback.onSharedElementEnd(names,views,null);
    }
  }
}

private static void bfsAddViewChildren(@NonNull final List<View> views,@NonNull final View startView){
  final int startIndex=views.size();
  if (containedBeforeIndex(views,startView,startIndex)) {
    return;
  }
  views.add(startView);
  for (int index=startIndex; index < views.size(); index++) {
    final View view=views.get(index);
    if (view instanceof ViewGroup) {
      ViewGroup viewGroup=(ViewGroup)view;
      final int childCount=viewGroup.getChildCount();
      for (int childIndex=0; childIndex < childCount; childIndex++) {
        final View child=viewGroup.getChildAt(childIndex);
        if (!containedBeforeIndex(views,child,startIndex)) {
          views.add(child);
        }
      }
    }
  }
}

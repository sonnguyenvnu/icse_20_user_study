public static void setTargets(@NonNull Transition transition,@NonNull View nonExistentView,@NonNull List<View> sharedViews){
  final List<View> views=transition.getTargets();
  views.clear();
  final int count=sharedViews.size();
  for (int i=0; i < count; i++) {
    final View view=sharedViews.get(i);
    bfsAddViewChildren(views,view);
  }
  views.add(nonExistentView);
  sharedViews.add(nonExistentView);
  addTargets(transition,sharedViews);
}

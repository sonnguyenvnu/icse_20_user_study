public static BaseBehavior from(View view){
  ViewGroup.LayoutParams params=view.getLayoutParams();
  if (!(params instanceof CoordinatorLayout.LayoutParams)) {
    throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
  }
  CoordinatorLayout.Behavior behavior=((CoordinatorLayout.LayoutParams)params).getBehavior();
  if (!(behavior instanceof BaseBehavior)) {
    throw new IllegalArgumentException("The view is not associated with BaseBehavior");
  }
  return (BaseBehavior)behavior;
}

public static <V extends View>FabScaleBehavior from(V view){
  ViewGroup.LayoutParams params=view.getLayoutParams();
  if (!(params instanceof CoordinatorLayout.LayoutParams)) {
    throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
  }
  CoordinatorLayout.Behavior behavior=((CoordinatorLayout.LayoutParams)params).getBehavior();
  if (!(behavior instanceof FabScaleBehavior)) {
    throw new IllegalArgumentException("The view is not associated with ScaleDownShowBehavior");
  }
  return (FabScaleBehavior)behavior;
}

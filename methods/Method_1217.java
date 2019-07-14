public static TransitionSet createTransitionSet(ScalingUtils.ScaleType fromScale,ScalingUtils.ScaleType toScale,@Nullable PointF fromFocusPoint,@Nullable PointF toFocusPoint){
  TransitionSet transitionSet=new TransitionSet();
  transitionSet.addTransition(new ChangeBounds());
  transitionSet.addTransition(new DraweeTransition(fromScale,toScale,fromFocusPoint,toFocusPoint));
  return transitionSet;
}

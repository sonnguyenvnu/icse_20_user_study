private static Animator createAnimator(View target,AnimatedProperty animatedProperty,float finalValue){
  final Property viewAnimatorProperty=getViewAnimatorProperty(animatedProperty);
  return ObjectAnimator.ofFloat(target,viewAnimatorProperty,finalValue);
}

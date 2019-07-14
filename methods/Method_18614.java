private void createAnimationsForTransitionUnit(TransitionUnit transition,TransitionId transitionId,ArrayList<AnimationBinding> outList){
  final Transition.AnimationTarget animationTarget=transition.getAnimationTarget();
switch (animationTarget.propertyTarget.propertyTargetType) {
case AUTO_LAYOUT:
    for (int i=0; i < AnimatedProperties.AUTO_LAYOUT_PROPERTIES.length; i++) {
      final AnimationBinding createdAnimation=maybeCreateAnimation(transition,transitionId,AnimatedProperties.AUTO_LAYOUT_PROPERTIES[i]);
      if (createdAnimation != null) {
        outList.add(createdAnimation);
      }
    }
  break;
case SET:
final AnimatedProperty[] properties=(AnimatedProperty[])animationTarget.propertyTarget.propertyTargetExtraData;
for (int i=0; i < properties.length; i++) {
final AnimationBinding createdAnimation=maybeCreateAnimation(transition,transitionId,properties[i]);
if (createdAnimation != null) {
  outList.add(createdAnimation);
}
}
break;
case SINGLE:
final AnimationBinding createdAnimation=maybeCreateAnimation(transition,transitionId,(AnimatedProperty)animationTarget.propertyTarget.propertyTargetExtraData);
if (createdAnimation != null) {
outList.add(createdAnimation);
}
break;
}
}

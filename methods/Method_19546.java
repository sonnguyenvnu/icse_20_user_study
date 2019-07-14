@OnCreateTransition static Transition onCreateTransition(ComponentContext c,@Prop int size,@Prop int movingDirection){
  if (movingDirection == DIRECTION_NONE) {
    return null;
  }
  final String[] keys=new String[size];
  for (int index=0; index < size; ++index) {
    keys[index]="dot" + index;
  }
  final boolean movingRight=movingDirection == DIRECTION_RIGHT;
  return Transition.create(Transition.TransitionKeyType.GLOBAL,keys).animate(AnimatedProperties.X,AnimatedProperties.Y,AnimatedProperties.HEIGHT,AnimatedProperties.WIDTH).animator(ANIMATOR).animate(AnimatedProperties.X).animator(ANIMATOR).appearFrom(DimensionValue.widthPercentageOffset(movingRight ? 100 : -100)).disappearTo(DimensionValue.widthPercentageOffset(movingRight ? -100 : 100)).animate(AnimatedProperties.SCALE).animator(ANIMATOR).appearFrom(0).disappearTo(0);
}

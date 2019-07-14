@Override public float resolve(Resolver resolver,PropertyHandle propertyHandle){
  final float currentValue=resolver.getCurrentState(propertyHandle);
switch (mType) {
case ABSOLUTE:
    return mValue;
case OFFSET:
  return mValue + currentValue;
case OFFSET_WIDTH_PERCENTAGE:
final float width=resolver.getCurrentState(new PropertyHandle(propertyHandle.getTransitionId(),AnimatedProperties.WIDTH));
return mValue / 100 * width + currentValue;
case OFFSET_HEIGHT_PERCENTAGE:
final float height=resolver.getCurrentState(new PropertyHandle(propertyHandle.getTransitionId(),AnimatedProperties.HEIGHT));
return mValue / 100 * height + currentValue;
default :
throw new RuntimeException("Missing RuntimeValue type: " + mType);
}
}

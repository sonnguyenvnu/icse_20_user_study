private PropertyValuesHolder holder(float[] fractions,Property property,float[] values){
  ensurePair(fractions.length,values.length);
  Keyframe[] keyframes=new Keyframe[fractions.length];
  for (int i=0; i < values.length; i++) {
    keyframes[i]=Keyframe.ofFloat(fractions[i],values[i]);
  }
  PropertyValuesHolder valuesHolder=PropertyValuesHolder.ofKeyframe(property,keyframes);
  propertyValuesHolders.add(valuesHolder);
  return valuesHolder;
}

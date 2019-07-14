private static void setPropertyValue(AnimatedProperty property,float value,OutputUnitsAffinityGroup<Object> mountContentGroup){
  for (int i=0, size=mountContentGroup.size(); i < size; i++) {
    property.set(mountContentGroup.getAt(i),value);
  }
}

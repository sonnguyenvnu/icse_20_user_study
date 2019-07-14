private static void resetProperty(AnimatedProperty property,OutputUnitsAffinityGroup<Object> mountContentGroup){
  for (int i=0, size=mountContentGroup.size(); i < size; i++) {
    property.reset(mountContentGroup.getAt(i));
  }
}

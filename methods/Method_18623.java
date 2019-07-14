private static float getPropertyValue(AnimatedProperty property,OutputUnitsAffinityGroup<LayoutOutput> mountContentGroup){
  return property.get(mountContentGroup.getMostSignificantUnit());
}

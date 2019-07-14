private void recursivelySetChildClippingForGroup(OutputUnitsAffinityGroup<Object> mountContentGroup,boolean clipChildren){
  recursivelySetChildClipping(mountContentGroup.get(OutputUnitType.HOST),clipChildren);
}

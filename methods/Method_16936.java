private void addWeight(){
  if (!context.generateFeatures.contains(Feature.MAXIMUM_WEIGHT)) {
    return;
  }
  context.nodeSubtype.addField(int.class,"weight").addMethod(newGetter(Strength.STRONG,TypeName.INT,"weight",Visibility.IMMEDIATE)).addMethod(newSetter(TypeName.INT,"weight",Visibility.IMMEDIATE));
  context.constructorByKey.addStatement("this.$N = $N","weight","weight");
  context.constructorByKeyRef.addStatement("this.$N = $N","weight","weight");
  context.nodeSubtype.addField(int.class,"policyWeight").addMethod(newGetter(Strength.STRONG,TypeName.INT,"policyWeight",Visibility.IMMEDIATE)).addMethod(newSetter(TypeName.INT,"policyWeight",Visibility.IMMEDIATE));
}

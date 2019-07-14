private void addAccessExpiration(){
  if (!context.generateFeatures.contains(Feature.EXPIRE_ACCESS)) {
    return;
  }
  context.nodeSubtype.addField(newFieldOffset(context.className,"accessTime")).addField(long.class,"accessTime",Modifier.VOLATILE).addMethod(newGetter(Strength.STRONG,TypeName.LONG,"accessTime",Visibility.LAZY)).addMethod(newSetter(TypeName.LONG,"accessTime",Visibility.LAZY));
  addTimeConstructorAssignment(context.constructorByKey,"accessTime");
  addTimeConstructorAssignment(context.constructorByKeyRef,"accessTime");
}

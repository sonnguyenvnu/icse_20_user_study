private void addWriteExpiration(){
  if (!Feature.useWriteTime(context.parentFeatures) && Feature.useWriteTime(context.generateFeatures)) {
    context.nodeSubtype.addField(newFieldOffset(context.className,"writeTime")).addField(long.class,"writeTime",Modifier.VOLATILE).addMethod(newGetter(Strength.STRONG,TypeName.LONG,"writeTime",Visibility.LAZY)).addMethod(newSetter(TypeName.LONG,"writeTime",Visibility.LAZY));
    addTimeConstructorAssignment(context.constructorByKey,"writeTime");
    addTimeConstructorAssignment(context.constructorByKeyRef,"writeTime");
  }
}

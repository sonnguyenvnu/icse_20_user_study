static ApexQualifiedName ofNestedClass(ApexQualifiedName parent,ASTUserClassOrInterface astUserClass){
  String[] classes=Arrays.copyOf(parent.classes,parent.classes.length + 1);
  classes[classes.length - 1]=astUserClass.getImage();
  return new ApexQualifiedName(parent.nameSpace,classes,null);
}

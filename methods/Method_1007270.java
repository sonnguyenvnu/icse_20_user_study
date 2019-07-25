static AttributeInfo read(ConstPool cp,DataInputStream in) throws IOException {
  int name=in.readUnsignedShort();
  String nameStr=cp.getUtf8Info(name);
  char first=nameStr.charAt(0);
  if (first < 'E')   if (nameStr.equals(AnnotationDefaultAttribute.tag))   return new AnnotationDefaultAttribute(cp,name,in);
 else   if (nameStr.equals(BootstrapMethodsAttribute.tag))   return new BootstrapMethodsAttribute(cp,name,in);
 else   if (nameStr.equals(CodeAttribute.tag))   return new CodeAttribute(cp,name,in);
 else   if (nameStr.equals(ConstantAttribute.tag))   return new ConstantAttribute(cp,name,in);
 else   if (nameStr.equals(DeprecatedAttribute.tag))   return new DeprecatedAttribute(cp,name,in);
  if (first < 'M')   if (nameStr.equals(EnclosingMethodAttribute.tag))   return new EnclosingMethodAttribute(cp,name,in);
 else   if (nameStr.equals(ExceptionsAttribute.tag))   return new ExceptionsAttribute(cp,name,in);
 else   if (nameStr.equals(InnerClassesAttribute.tag))   return new InnerClassesAttribute(cp,name,in);
 else   if (nameStr.equals(LineNumberAttribute.tag))   return new LineNumberAttribute(cp,name,in);
 else   if (nameStr.equals(LocalVariableAttribute.tag))   return new LocalVariableAttribute(cp,name,in);
 else   if (nameStr.equals(LocalVariableTypeAttribute.tag))   return new LocalVariableTypeAttribute(cp,name,in);
  if (first < 'S')   if (nameStr.equals(MethodParametersAttribute.tag))   return new MethodParametersAttribute(cp,name,in);
 else   if (nameStr.equals(NestHostAttribute.tag))   return new NestHostAttribute(cp,name,in);
 else   if (nameStr.equals(NestMembersAttribute.tag))   return new NestMembersAttribute(cp,name,in);
 else   if (nameStr.equals(AnnotationsAttribute.visibleTag) || nameStr.equals(AnnotationsAttribute.invisibleTag))   return new AnnotationsAttribute(cp,name,in);
 else   if (nameStr.equals(ParameterAnnotationsAttribute.visibleTag) || nameStr.equals(ParameterAnnotationsAttribute.invisibleTag))   return new ParameterAnnotationsAttribute(cp,name,in);
 else   if (nameStr.equals(TypeAnnotationsAttribute.visibleTag) || nameStr.equals(TypeAnnotationsAttribute.invisibleTag))   return new TypeAnnotationsAttribute(cp,name,in);
  if (first >= 'S')   if (nameStr.equals(SignatureAttribute.tag))   return new SignatureAttribute(cp,name,in);
 else   if (nameStr.equals(SourceFileAttribute.tag))   return new SourceFileAttribute(cp,name,in);
 else   if (nameStr.equals(SyntheticAttribute.tag))   return new SyntheticAttribute(cp,name,in);
 else   if (nameStr.equals(StackMap.tag))   return new StackMap(cp,name,in);
 else   if (nameStr.equals(StackMapTable.tag))   return new StackMapTable(cp,name,in);
  return new AttributeInfo(cp,name,in);
}

void prune(ConstPool cp){
  List<AttributeInfo> newAttributes=new ArrayList<AttributeInfo>();
  AttributeInfo invisibleAnnotations=getAttribute(AnnotationsAttribute.invisibleTag);
  if (invisibleAnnotations != null) {
    invisibleAnnotations=invisibleAnnotations.copy(cp,null);
    newAttributes.add(invisibleAnnotations);
  }
  AttributeInfo visibleAnnotations=getAttribute(AnnotationsAttribute.visibleTag);
  if (visibleAnnotations != null) {
    visibleAnnotations=visibleAnnotations.copy(cp,null);
    newAttributes.add(visibleAnnotations);
  }
  AttributeInfo signature=getAttribute(SignatureAttribute.tag);
  if (signature != null) {
    signature=signature.copy(cp,null);
    newAttributes.add(signature);
  }
  int index=getConstantValue();
  if (index != 0) {
    index=constPool.copy(index,cp,null);
    newAttributes.add(new ConstantAttribute(cp,index));
  }
  attribute=newAttributes;
  name=cp.addUtf8Info(getName());
  descriptor=cp.addUtf8Info(getDescriptor());
  constPool=cp;
}

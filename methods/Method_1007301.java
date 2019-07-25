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
  AttributeInfo parameterInvisibleAnnotations=getAttribute(ParameterAnnotationsAttribute.invisibleTag);
  if (parameterInvisibleAnnotations != null) {
    parameterInvisibleAnnotations=parameterInvisibleAnnotations.copy(cp,null);
    newAttributes.add(parameterInvisibleAnnotations);
  }
  AttributeInfo parameterVisibleAnnotations=getAttribute(ParameterAnnotationsAttribute.visibleTag);
  if (parameterVisibleAnnotations != null) {
    parameterVisibleAnnotations=parameterVisibleAnnotations.copy(cp,null);
    newAttributes.add(parameterVisibleAnnotations);
  }
  AnnotationDefaultAttribute defaultAttribute=(AnnotationDefaultAttribute)getAttribute(AnnotationDefaultAttribute.tag);
  if (defaultAttribute != null)   newAttributes.add(defaultAttribute);
  ExceptionsAttribute ea=getExceptionsAttribute();
  if (ea != null)   newAttributes.add(ea);
  AttributeInfo signature=getAttribute(SignatureAttribute.tag);
  if (signature != null) {
    signature=signature.copy(cp,null);
    newAttributes.add(signature);
  }
  attribute=newAttributes;
  name=cp.addUtf8Info(getName());
  descriptor=cp.addUtf8Info(getDescriptor());
  constPool=cp;
}

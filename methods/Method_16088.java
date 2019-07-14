@SneakyThrows public Proxy<I> addField(String code,Class<? extends java.lang.annotation.Annotation> annotation,Map<String,Object> annotationProperties){
  return handleException(() -> {
    CtField ctField=CtField.make(code,ctClass);
    if (null != annotation) {
      ConstPool constPool=ctClass.getClassFile().getConstPool();
      AnnotationsAttribute attributeInfo=new AnnotationsAttribute(constPool,AnnotationsAttribute.visibleTag);
      Annotation ann=new javassist.bytecode.annotation.Annotation(annotation.getName(),constPool);
      if (null != annotationProperties) {
        annotationProperties.forEach((key,value) -> {
          MemberValue memberValue=createMemberValue(value,constPool);
          if (memberValue != null) {
            ann.addMemberValue(key,memberValue);
          }
        }
);
      }
      attributeInfo.addAnnotation(ann);
      ctField.getFieldInfo().addAttribute(attributeInfo);
    }
    ctClass.addField(ctField);
  }
);
}

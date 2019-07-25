@Override public List<SourceMethod> process(ProcessorContext context,TypeElement mapperTypeElement,Void sourceModel){
  this.messager=context.getMessager();
  this.typeFactory=context.getTypeFactory();
  this.accessorNaming=context.getAccessorNaming();
  this.typeUtils=context.getTypeUtils();
  this.elementUtils=context.getElementUtils();
  this.messager.note(0,Message.PROCESSING_NOTE,mapperTypeElement);
  MapperConfiguration mapperConfig=MapperConfiguration.getInstanceOn(mapperTypeElement);
  if (mapperConfig != null) {
    this.messager.note(0,Message.CONFIG_NOTE,mapperConfig.getClass().getName());
  }
  if (!mapperConfig.isValid()) {
    throw new AnnotationProcessingException("Couldn't retrieve @Mapper annotation",mapperTypeElement,mapperConfig.getAnnotationMirror());
  }
  List<SourceMethod> prototypeMethods=retrievePrototypeMethods(mapperTypeElement,mapperConfig);
  return retrieveMethods(mapperTypeElement,mapperTypeElement,mapperConfig,prototypeMethods);
}

@Override public Mapper process(ProcessorContext context,TypeElement mapperTypeElement,Mapper mapper){
  if (!context.isErroneous()) {
    writeToSourceFile(context.getFiler(),mapper,mapperTypeElement);
    return mapper;
  }
  return null;
}

@Override public Mapper process(ProcessorContext context,TypeElement mapperTypeElement,Mapper mapper){
  this.typeFactory=context.getTypeFactory();
  MapperConfiguration mapperConfiguration=MapperConfiguration.getInstanceOn(mapperTypeElement);
  String componentModel=mapperConfiguration.componentModel(context.getOptions());
  InjectionStrategyPrism injectionStrategy=mapperConfiguration.getInjectionStrategy();
  if (!getComponentModelIdentifier().equalsIgnoreCase(componentModel)) {
    return mapper;
  }
  for (  Annotation typeAnnotation : getTypeAnnotations(mapper)) {
    mapper.addAnnotation(typeAnnotation);
  }
  if (!requiresGenerationOfDecoratorClass()) {
    mapper.removeDecorator();
  }
 else   if (mapper.getDecorator() != null) {
    adjustDecorator(mapper,injectionStrategy);
  }
  List<Annotation> annotations=getMapperReferenceAnnotations();
  ListIterator<Field> iterator=mapper.getFields().listIterator();
  while (iterator.hasNext()) {
    Field reference=iterator.next();
    if (reference instanceof MapperReference) {
      iterator.remove();
      iterator.add(replacementMapperReference(reference,annotations,injectionStrategy));
    }
  }
  if (injectionStrategy == InjectionStrategyPrism.CONSTRUCTOR) {
    buildConstructors(mapper);
  }
  return mapper;
}

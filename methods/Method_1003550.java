@Override public Mapper process(ProcessorContext context,TypeElement mapperTypeElement,List<SourceMethod> sourceModel){
  this.elementUtils=context.getElementUtils();
  this.typeUtils=context.getTypeUtils();
  this.messager=context.getMessager();
  this.options=context.getOptions();
  this.versionInformation=context.getVersionInformation();
  this.typeFactory=context.getTypeFactory();
  this.accessorNaming=context.getAccessorNaming();
  MapperConfiguration mapperConfig=MapperConfiguration.getInstanceOn(mapperTypeElement);
  List<MapperReference> mapperReferences=initReferencedMappers(mapperTypeElement,mapperConfig);
  MappingBuilderContext ctx=new MappingBuilderContext(typeFactory,elementUtils,typeUtils,messager,accessorNaming,options,new MappingResolverImpl(messager,elementUtils,typeUtils,typeFactory,new ArrayList<>(sourceModel),mapperReferences),mapperTypeElement,Collections.unmodifiableList(sourceModel),mapperReferences);
  this.mappingContext=ctx;
  return getMapper(mapperTypeElement,mapperConfig,sourceModel);
}

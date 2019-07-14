public void createClasses() throws IOException {
  TypeElement baseBuilder;
  try {
    baseBuilder=processingEnv.getElementUtils().getTypeElement(configuration.baseBuilderClass().getName());
  }
 catch (  MirroredTypeException e) {
    baseBuilder=MoreTypes.asTypeElement(e.getTypeMirror());
  }
  final List<Element> elements=new ModelBuilder(baseAnnotation,new ElementFactory(processingEnv.getElementUtils()),baseBuilder,processingEnv.getMessager()).build();
  createBuilderClass(elements);
  createConfigClass(elements);
  if (configuration.isPlugin()) {
    createBuilderInterface(elements);
    createFactoryClass();
  }
}

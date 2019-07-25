public void build(DozerBuilder.ClassDefinitionBuilder typeBuilder){
  typeBuilder.beanFactory(this.beanFactory);
  typeBuilder.createMethod(this.createMethod);
  typeBuilder.factoryBeanId(this.factoryBeanId);
  typeBuilder.mapEmptyString(this.mapEmptyString);
  typeBuilder.mapNull(this.mapNull);
  typeBuilder.mapGetMethod(this.mapGetMethod);
  typeBuilder.mapSetMethod(this.mapSetMethod);
  typeBuilder.isAccessible(this.isAccessible);
  typeBuilder.skipConstructor(this.skipConstructor);
}

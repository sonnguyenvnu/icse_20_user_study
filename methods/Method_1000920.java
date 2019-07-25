public static ClassNameIdResolver construct(JavaType baseType,MapperConfig<?> config,PolymorphicTypeValidator ptv){
  return new ClassNameIdResolver(baseType,config.getTypeFactory(),ptv);
}

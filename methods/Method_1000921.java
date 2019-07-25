public static MinimalClassNameIdResolver construct(JavaType baseType,MapperConfig<?> config,PolymorphicTypeValidator ptv){
  return new MinimalClassNameIdResolver(baseType,config.getTypeFactory(),ptv);
}

private SchemaDefinitionReader createdSchemaDefinitionReader(){
  return new SchemaDefinitionReader(this.schemaDefinition){
    @Override protected ImmutableList<GraphQLFieldDefinition> extraMutations(){
      return SchemaModule.this.extraMutations();
    }
    @Override protected Function<DataFetchingEnvironment,Object> handleParameter(    Method method,    int parameterIndex){
      Annotation[] annotations=method.getParameterAnnotations()[parameterIndex];
      Annotation qualifier=null;
      for (      Annotation annotation : annotations) {
        if (com.google.inject.internal.Annotations.isBindingAnnotation(annotation.annotationType())) {
          qualifier=annotation;
        }
      }
      final java.lang.reflect.Type[] genericParameterTypes=method.getGenericParameterTypes();
      Key<?> key=qualifier == null ? Key.get(genericParameterTypes[parameterIndex]) : Key.get(genericParameterTypes[parameterIndex],qualifier);
      final com.google.inject.Provider<?> provider=binder().getProvider(key);
      return (ignored) -> provider;
    }
  }
;
}

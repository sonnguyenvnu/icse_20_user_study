/** 
 * Returns a Builder  {@link GeneratedType} for {@code type}.
 * @throws CannotGenerateCodeException if code cannot be generated, e.g. if the type is private
 */
GeneratedType analyse(TypeElement type) throws CannotGenerateCodeException {
  PackageElement pkg=elements.getPackageOf(type);
  verifyType(type,pkg);
  QualifiedName generatedBuilder=QualifiedName.of(pkg.getQualifiedName().toString(),generatedBuilderSimpleName(type));
  List<? extends TypeParameterElement> typeParameters=type.getTypeParameters();
  DeclaredType builder=tryFindBuilder(generatedBuilder,type).orElse(null);
  if (builder == null) {
    return new GeneratedStub(QualifiedName.of(type),generatedBuilder.withParameters(typeParameters));
  }
  ImmutableSet<ExecutableElement> methods=methodsOn(type,elements,errorType -> {
    throw new CannotGenerateCodeException();
  }
);
  Datatype.Builder constructionAndExtension=constructionAndExtension(builder);
  QualifiedName valueType=generatedBuilder.nestedType("Value");
  QualifiedName partialType=generatedBuilder.nestedType("Partial");
  QualifiedName propertyType=generatedBuilder.nestedType("Property");
  Datatype.Builder datatypeBuilder=new Datatype.Builder().setType(QualifiedName.of(type).withParameters(typeParameters)).setInterfaceType(type.getKind().isInterface()).mergeFrom(constructionAndExtension).setGeneratedBuilder(generatedBuilder.withParameters(typeParameters)).setValueType(valueType.withParameters(typeParameters)).setPartialType(partialType.withParameters(typeParameters)).setPropertyEnum(propertyType.withParameters()).putAllStandardMethodUnderrides(findUnderriddenMethods(methods)).setHasToBuilderMethod(hasToBuilderMethod(builder,constructionAndExtension.isExtensible(),methods)).setBuilderSerializable(shouldBuilderBeSerializable(builder)).setBuilder(Type.from(builder));
  if (datatypeBuilder.getBuilderFactory().isPresent() && !datatypeBuilder.getHasToBuilderMethod()) {
    datatypeBuilder.setRebuildableType(generatedBuilder.nestedType("Rebuildable").withParameters(typeParameters));
  }
  Datatype baseDatatype=datatypeBuilder.build();
  Map<Property,PropertyCodeGenerator> generatorsByProperty=pickPropertyGenerators(type,baseDatatype,builder,removeNonGetterMethods(builder,methods));
  datatypeBuilder.mergeFrom(gwtMetadata(type,baseDatatype,generatorsByProperty));
  return new GeneratedBuilder(datatypeBuilder.build(),generatorsByProperty);
}

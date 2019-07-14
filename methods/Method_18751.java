static TypeSpec generateTypeSpec(PsiType type){
  return type.accept(new PsiTypeVisitor<TypeSpec>(){
    @Override public TypeSpec visitType(    PsiType type){
      return new TypeSpec(getTypeName(type));
    }
    @Override public TypeSpec visitClassType(    PsiClassType classType){
      final PsiClass psiClass=classType.resolve();
      final String qualifiedName=psiClass.getQualifiedName() != null ? psiClass.getQualifiedName() : classType.getCanonicalText();
      final PsiClass superClass=psiClass.getSuperClass();
      final PsiReferenceList superInterfaces=psiClass.getImplementsList();
      final List<TypeSpec> superInterfaceSpecs=superInterfaces != null && superInterfaces.getReferencedTypes() != null && superInterfaces.getReferencedTypes().length > 0 ? Arrays.stream(superInterfaces.getReferencedTypes()).map(PsiTypeUtils::generateTypeSpec).collect(Collectors.toList()) : Collections.emptyList();
      final List<TypeSpec> typeArguments=ClassName.bestGuess(qualifiedName).equals(ClassNames.DIFF) || superInterfaceSpecs.stream().anyMatch(typeSpec -> typeSpec.isSubInterface(ClassNames.COLLECTION)) ? Arrays.stream(classType.getParameters()).map(PsiTypeUtils::generateTypeSpec).collect(Collectors.toList()) : Collections.emptyList();
      TypeSpec superClassSpec=(superClass != null && !superClass.isInterface()) ? generateTypeSpec(PsiTypesUtil.getClassType(superClass)) : null;
      return new TypeSpec.DeclaredTypeSpec(getTypeName(type),qualifiedName,() -> superClassSpec,copyOf(superInterfaceSpecs),copyOf(typeArguments));
    }
  }
);
}

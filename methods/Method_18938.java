/** 
 * This method will "expand" the typeArguments of the given type, only if the type is a  {@link ClassNames#DIFF} or a {@link java.util.Collection}. Otherwise the typeArguments won't be traversed and recorded.
 */
public static TypeSpec generateTypeSpec(TypeMirror type){
  final TypeSpec defaultValue=new TypeSpec(safelyGetTypeName(type),type.getKind() != TypeKind.ERROR);
  return type.accept(new SimpleTypeVisitor6<TypeSpec,Void>(defaultValue){
    @Override public TypeSpec visitDeclared(    DeclaredType t,    Void aVoid){
      final TypeElement typeElement=(TypeElement)t.asElement();
      final String qualifiedName=typeElement.getQualifiedName().toString();
      final Supplier<TypeSpec> superclass=new SimpleMemoizingSupplier<>(() -> {
        final TypeMirror mirror=typeElement.getSuperclass();
        return mirror.getKind() != TypeKind.DECLARED ? null : generateTypeSpec(mirror);
      }
);
      final List<? extends TypeMirror> mirrors=typeElement.getInterfaces();
      final List<TypeSpec> superinterfaces=mirrors != null && !mirrors.isEmpty() ? mirrors.stream().filter(mirror -> mirror.getKind() == TypeKind.DECLARED).map(SpecModelUtils::generateTypeSpec).collect(Collectors.toList()) : Collections.emptyList();
      final List<TypeSpec> typeArguments=ClassName.bestGuess(qualifiedName).equals(ClassNames.DIFF) || superinterfaces.stream().anyMatch(typeSpec -> typeSpec.isSubInterface(ClassNames.COLLECTION)) ? ((DeclaredType)type).getTypeArguments().stream().map(SpecModelUtils::generateTypeSpec).collect(Collectors.toList()) : Collections.emptyList();
      return new TypeSpec.DeclaredTypeSpec(safelyGetTypeName(t),qualifiedName,superclass,copyOf(superinterfaces),copyOf(typeArguments));
    }
  }
,null);
}

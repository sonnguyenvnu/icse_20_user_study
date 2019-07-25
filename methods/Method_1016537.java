public static Type from(Class<?> cls){
  return new TypeImpl(QualifiedName.of(cls),asList(cls.getTypeParameters()));
}

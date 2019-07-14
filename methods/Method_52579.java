public static boolean isEither(TypeNode n,Class<?> class1,Class<?> class2){
  return subclasses(n,class1) || subclasses(n,class2);
}

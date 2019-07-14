static boolean isIterableType(TypeElement element){
  return isSubtypeOfType(element.asType(),"java.lang.Iterable<?>");
}

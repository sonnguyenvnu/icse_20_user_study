public static SpecElementType determine(TypeElement element){
  if (isKotlinSingleton(element)) {
    return SpecElementType.KOTLIN_SINGLETON;
  }
  return SpecElementType.JAVA_CLASS;
}

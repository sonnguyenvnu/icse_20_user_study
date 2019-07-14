public static boolean isKotlin(Class clazz){
  if (kotlin_metadata == null && !kotlin_metadata_error) {
    try {
      kotlin_metadata=Class.forName("kotlin.Metadata");
    }
 catch (    Throwable e) {
      kotlin_metadata_error=true;
    }
  }
  return kotlin_metadata != null && clazz.isAnnotationPresent(kotlin_metadata);
}

public static boolean isSpringCloud(){
  return ClassUtils.isPresent(SPRING_CLOUD_MARK_NAME,null);
}

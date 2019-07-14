private static <T extends AccessibleObject & Member>void validateMember(T object){
  int modifiers=object.getModifiers();
  if ((modifiers & (PRIVATE | STATIC)) != 0) {
    throw new IllegalStateException(object.getDeclaringClass().getName() + "." + object.getName() + " must not be private or static");
  }
  if ((modifiers & PUBLIC) == 0) {
    object.setAccessible(true);
  }
}

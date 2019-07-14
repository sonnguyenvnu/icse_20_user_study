/** 
 * Appends the descriptor of the given class to the given string builder.
 * @param clazz the class whose descriptor must be computed.
 * @param stringBuilder the string builder to which the descriptor must be appended.
 */
private static void appendDescriptor(final Class<?> clazz,final StringBuilder stringBuilder){
  Class<?> currentClass=clazz;
  while (currentClass.isArray()) {
    stringBuilder.append('[');
    currentClass=currentClass.getComponentType();
  }
  if (currentClass.isPrimitive()) {
    char descriptor;
    if (currentClass == Integer.TYPE) {
      descriptor='I';
    }
 else     if (currentClass == Void.TYPE) {
      descriptor='V';
    }
 else     if (currentClass == Boolean.TYPE) {
      descriptor='Z';
    }
 else     if (currentClass == Byte.TYPE) {
      descriptor='B';
    }
 else     if (currentClass == Character.TYPE) {
      descriptor='C';
    }
 else     if (currentClass == Short.TYPE) {
      descriptor='S';
    }
 else     if (currentClass == Double.TYPE) {
      descriptor='D';
    }
 else     if (currentClass == Float.TYPE) {
      descriptor='F';
    }
 else     if (currentClass == Long.TYPE) {
      descriptor='J';
    }
 else {
      throw new AssertionError();
    }
    stringBuilder.append(descriptor);
  }
 else {
    stringBuilder.append('L').append(getInternalName(currentClass)).append(';');
  }
}

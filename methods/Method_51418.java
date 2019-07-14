private static void serializedTypeIdOn(Class<?> type,StringBuilder sb){
  Class<?> arrayType=type.getComponentType();
  if (arrayType == null) {
    sb.append(shortestNameFor(type));
    return;
  }
  sb.append(shortestNameFor(arrayType)).append(ARRAY_FLAG);
}

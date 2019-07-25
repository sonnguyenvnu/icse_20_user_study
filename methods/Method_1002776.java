private static int index(AttributeKey<?> key){
  return key.id() & MASK;
}

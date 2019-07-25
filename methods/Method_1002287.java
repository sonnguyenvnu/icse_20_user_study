public List<Variable> fields(){
  int fieldOffset=SpscOffHeapFixedSizeRingBuffer.MESSAGE_INDICATOR_SIZE;
  List<Variable> fields=new ArrayList<Variable>();
  for (  Method method : inspector.getters) {
    Primitive type=Primitive.of(method.getReturnType());
    String name=method.getName().substring(3);
    fields.add(new Variable(type.javaEquivalent.getName(),name,fieldOffset,type.unsafeMethodSuffix()));
    fieldOffset+=type.sizeInBytes;
  }
  return fields;
}

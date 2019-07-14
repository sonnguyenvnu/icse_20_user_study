private static FieldDescriptor getField(Descriptor descriptor,String name){
  return descriptor.findFieldByName(CAMEL_TO_UNDERSCORE.convert(name));
}

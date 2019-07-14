private static String functionalInterfaceSignature(VisitorState state,Type type){
  Types types=state.getTypes();
  if (!maybeFunctionalInterface(type,types,state)) {
    return Signatures.descriptor(type,types);
  }
  Type descriptorType=types.findDescriptorType(type);
  List<Type> fiparams=descriptorType.getParameterTypes();
  String result=fiparams.isEmpty() ? Signatures.descriptor(descriptorType.getReturnType(),types) : "_";
  return String.format("(%s)->%s",fiparams.stream().map(t -> Signatures.descriptor(t,types)).collect(joining(",")),result);
}

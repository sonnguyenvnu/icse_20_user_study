@Override public Object evaluate(Class<?> type){
  try {
    return mapper.readValue(valueNode.traverse(mapper.getFactory().getCodec()),type);
  }
 catch (  Exception o_O) {
    throw new PatchException(String.format("Could not read %s into %s!",valueNode,type),o_O);
  }
}

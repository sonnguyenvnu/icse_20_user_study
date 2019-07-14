private MethodParameterMetadata toMethodParameterMetadata(int index,Parameter parameter){
  MethodParameterMetadata metadata=new MethodParameterMetadata();
  metadata.setIndex(index);
  metadata.setName(parameter.getName());
  metadata.setType(parameter.getType().getTypeName());
  return metadata;
}

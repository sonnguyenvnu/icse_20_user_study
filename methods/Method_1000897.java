List<AnnotatedField> collect(TypeResolutionContext tc,TypeFactory typeFactory,JavaType type,Class<?> primaryMixIn){
  Map<String,FieldBuilder> foundFields=_findFields(tc,typeFactory,type,primaryMixIn,null);
  if (foundFields == null) {
    return Collections.emptyList();
  }
  List<AnnotatedField> result=new ArrayList<>(foundFields.size());
  for (  FieldBuilder b : foundFields.values()) {
    result.add(b.build());
  }
  return result;
}

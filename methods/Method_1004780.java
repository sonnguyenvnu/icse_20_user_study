private FieldExtractor init(String fieldName,List<String> pathList){
  if (fieldName != null) {
    Object constant=initConstant(fieldName);
    if (constant != null) {
      return new FixedFieldExtractor(constant);
    }
 else {
      return createJsonFieldExtractor(fieldName,pathList,true);
    }
  }
  return null;
}

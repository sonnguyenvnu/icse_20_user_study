public void reset(){
  if (fieldsValues != null)   fieldsValues.clear();
  source=null;
  type=null;
  id=null;
  requiredFields.addAll(BASE_REQUIRED_FIELDS);
  if (loadSource) {
    requiredFields.add(SourceFieldMapper.NAME);
  }
}

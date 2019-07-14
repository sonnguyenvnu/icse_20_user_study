/** 
 * ?es?field?????Object
 * @param fields
 * @return
 */
private Map<String,Object> toFieldsMap(Map<String,DocumentField> fields){
  Map<String,Object> result=new HashMap<>();
  for (  Entry<String,DocumentField> entry : fields.entrySet()) {
    if (entry.getValue().getValues().size() > 1) {
      result.put(entry.getKey(),entry.getValue().getValues());
    }
 else {
      result.put(entry.getKey(),entry.getValue().getValue());
    }
  }
  return result;
}

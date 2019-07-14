/** 
 * ?es?field?????Object
 * @param fields
 * @return
 * @throws SqlParseException
 */
private Map<String,Object> toAggsMap(Map<String,Aggregation> fields) throws SqlParseException {
  Map<String,Object> result=new HashMap<>();
  for (  Entry<String,Aggregation> entry : fields.entrySet()) {
    result.put(entry.getKey(),covenValue(entry.getValue()));
  }
  return result;
}

private String mapping(String key,Criteria criteria){
  if (key.contains(SqlScript.POINT)) {
    String[] arr=key.split("\\.");
    String clzName=arr[0];
    String property=arr[1];
    Parsed parsed=Parser.get(clzName);
    if (parsed == null)     throw new RuntimeException("Entity Bean Not Exist: " + BeanUtil.getByFirstUpper(key));
    String value=parsed.getTableName() + SqlScript.POINT + parsed.getMapper(property);
    return value;
  }
  if (criteria instanceof Criteria.ResultMappedCriteria) {
    Parsed parsed=Parser.get(key);
    if (parsed != null) {
      return parsed.getTableName();
    }
  }
  Class clz=criteria.getClz();
  Parsed parsed=Parser.get(clz);
  if (key.equals(BeanUtilX.getByFirstLower(clz.getSimpleName())))   return parsed.getTableName();
  String value=parsed.getMapper(key);
  if (value == null)   return key;
  return value;
}

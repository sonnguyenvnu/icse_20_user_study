/** 
 * Returns an array of param keys that belongs to provided bean. Optionally resolves the value of returned parameters.
 */
public String[] filterParametersForBeanName(String beanName,final boolean resolveReferenceParams){
  beanName=beanName + '.';
  List<String> list=new ArrayList<>();
  for (  Map.Entry<String,Object> entry : params.entrySet()) {
    String key=entry.getKey();
    if (!key.startsWith(beanName)) {
      continue;
    }
    list.add(key);
    if (!resolveReferenceParams) {
      continue;
    }
    String value=PropertiesUtil.resolveProperty(params,key);
    entry.setValue(value);
  }
  if (list.isEmpty()) {
    return StringPool.EMPTY_ARRAY;
  }
 else {
    return list.toArray(new String[0]);
  }
}

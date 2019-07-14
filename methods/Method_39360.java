/** 
 * Resolves bean names for give type.
 */
protected String[] resolveBeanNamesForType(final Class type){
  String[] beanNames=beanCollections.get(type);
  if (beanNames != null) {
    return beanNames;
  }
  ArrayList<String> list=new ArrayList<>();
  for (  Map.Entry<String,BeanDefinition> entry : beans.entrySet()) {
    BeanDefinition beanDefinition=entry.getValue();
    if (ClassUtil.isTypeOf(beanDefinition.type,type)) {
      String beanName=entry.getKey();
      list.add(beanName);
    }
  }
  if (list.isEmpty()) {
    beanNames=StringPool.EMPTY_ARRAY;
  }
 else {
    beanNames=list.toArray(new String[0]);
  }
  beanCollections.put(type,beanNames);
  return beanNames;
}

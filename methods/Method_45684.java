/** 
 * ?????????????
 * @param targetClass ???
 * @return Field list
 */
protected static List<Field> getSerializeFields(Class targetClass){
  List<Field> all=new ArrayList<Field>();
  for (Class<?> c=targetClass; c != Object.class && c != null; c=c.getSuperclass()) {
    Field[] fields=c.getDeclaredFields();
    for (    Field f : fields) {
      int mod=f.getModifiers();
      if (Modifier.isStatic(mod) || Modifier.isTransient(mod)) {
        continue;
      }
      JSONIgnore ignore=f.getAnnotation(JSONIgnore.class);
      if (ignore != null) {
        continue;
      }
      f.setAccessible(true);
      all.add(f);
    }
  }
  return all;
}

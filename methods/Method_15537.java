/** 
 * @param key
 * @param exactMatch
 * @return
 */
@JSONField(serialize=false) @Override public Object getWhere(String key,boolean exactMatch){
  if (exactMatch) {
    return where == null ? null : where.get(key);
  }
  Set<String> set=key == null || where == null ? null : where.keySet();
  if (set != null) {
synchronized (where) {
      if (where != null) {
        int index;
        for (        String k : set) {
          index=k.indexOf(key);
          if (index >= 0 && StringUtil.isName(k.substring(index)) == false) {
            return where.get(k);
          }
        }
      }
    }
  }
  return null;
}

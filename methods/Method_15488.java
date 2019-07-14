/** 
 * ?? @correct ??
 * @throws Exception 
 */
@Override public AbstractObjectParser parseCorrect() throws Exception {
  Set<String> set=correct == null ? null : new HashSet<>(correct.keySet());
  if (set != null && set.isEmpty() == false) {
    corrected=new HashMap<>();
    String value;
    String v;
    String[] posibleKeys;
    for (    String k : set) {
      v=k == null ? null : correct.getString(k);
      value=v == null ? null : request.getString(k);
      posibleKeys=value == null ? null : StringUtil.split(v);
      if (posibleKeys != null && posibleKeys.length > 0) {
        String rk=null;
        Pattern p;
        for (        String pk : posibleKeys) {
          p=pk == null ? null : COMPILE_MAP.get(pk);
          if (p != null && p.matcher(value).matches()) {
            rk=pk;
            break;
          }
        }
        if (rk == null) {
          throw new IllegalArgumentException("???????? " + k + ":" + value + " ??[" + v + "]???????");
        }
        request.put(rk,request.remove(k));
        corrected.put(k,rk);
      }
    }
  }
  return this;
}

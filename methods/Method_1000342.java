/** 
 * ????????? ???? Chain ?? <p> ????????? POJO ????? Map? <p> ?? FieldMatcher??????? FieldMatcher ???????????? Chain
 * @param obj ???????? POJO ????? Map
 * @param fm ???????null ????????
 * @return Chain ???null ???????????
 * @see org.nutz.dao.FieldMatcher
 */
public static Chain from(Object obj,FieldMatcher fm){
  if (null == obj)   return null;
  Chain c=null;
  if (obj instanceof Map<?,?>) {
    for (    Map.Entry<?,?> en : ((Map<?,?>)obj).entrySet()) {
      Object key=en.getKey();
      if (null == key)       continue;
      String name=key.toString();
      if (null != fm && !fm.match(name))       continue;
      Object v=en.getValue();
      if (null != fm) {
        if (null == v) {
          if (fm.isIgnoreNull())           continue;
        }
 else         if (fm.isIgnoreBlankStr() && v instanceof String && Strings.isBlank((String)v)) {
          continue;
        }
      }
      if (c == null) {
        c=Chain.make(name,v);
      }
 else {
        c=c.add(name,v);
      }
    }
  }
 else {
    Mirror<?> mirror=Mirror.me(obj.getClass());
    for (    Field f : mirror.getFields()) {
      if (null != fm && !fm.match(f.getName()))       continue;
      Object v=mirror.getValue(obj,f.getName());
      if (null == v) {
        if (fm != null && fm.isIgnoreNull())         continue;
      }
 else       if (fm != null && fm.isIgnoreBlankStr() && v instanceof String && Strings.isBlank((String)v)) {
        continue;
      }
      if (c == null) {
        c=Chain.make(f.getName(),v);
      }
 else {
        c=c.add(f.getName(),v);
      }
    }
  }
  return c;
}

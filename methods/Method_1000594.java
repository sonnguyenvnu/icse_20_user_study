/** 
 * ??????????? ???????: <ul> <li>???? null <li>????????? Number <li>??????? Map ????? </ul> ????????? equals ?????
 * @param a0 ????1
 * @param a1 ????2
 * @return ????
 */
public static boolean equals(Object a0,Object a1){
  if (a0 == a1)   return true;
  if (a0 == null && a1 == null)   return true;
  if (a0 == null || a1 == null)   return false;
  if (a0.equals(a1))   return true;
  Mirror<?> mi=Mirror.me(a0);
  if (mi.isSimple() || mi.is(Pattern.class)) {
    return a0.toString().equals(a1.toString());
  }
  if (!a0.getClass().isAssignableFrom(a1.getClass()) && !a1.getClass().isAssignableFrom(a0.getClass()))   return false;
  if (a0 instanceof Map && a1 instanceof Map) {
    Map<?,?> m1=(Map<?,?>)a0;
    Map<?,?> m2=(Map<?,?>)a1;
    if (m1.size() != m2.size())     return false;
    for (    Entry<?,?> e : m1.entrySet()) {
      Object key=e.getKey();
      if (!m2.containsKey(key) || !equals(m1.get(key),m2.get(key)))       return false;
    }
    return true;
  }
 else   if (a0.getClass().isArray() && a1.getClass().isArray()) {
    int len=Array.getLength(a0);
    if (len != Array.getLength(a1))     return false;
    for (int i=0; i < len; i++) {
      if (!equals(Array.get(a0,i),Array.get(a1,i)))       return false;
    }
    return true;
  }
 else   if (a0 instanceof Collection && a1 instanceof Collection) {
    Collection<?> c0=(Collection<?>)a0;
    Collection<?> c1=(Collection<?>)a1;
    if (c0.size() != c1.size())     return false;
    Iterator<?> it0=c0.iterator();
    Iterator<?> it1=c1.iterator();
    while (it0.hasNext()) {
      Object o0=it0.next();
      Object o1=it1.next();
      if (!equals(o0,o1))       return false;
    }
    return true;
  }
  return false;
}

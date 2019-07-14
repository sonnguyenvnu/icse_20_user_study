/** 
 * ????
 * @param opt
 * @param targetChild
 * @param real
 * @param creator 
 * @return
 * @throws Exception
 */
private static JSONObject operate(Operation opt,JSONObject targetChild,JSONObject real,SQLCreator creator) throws Exception {
  if (targetChild == null) {
    return real;
  }
  if (real == null) {
    throw new IllegalArgumentException("operate  real == null!!!");
  }
  Set<Entry<String,Object>> set=new LinkedHashSet<>(targetChild.entrySet());
  String tk;
  Object tv;
  for (  Entry<String,Object> e : set) {
    tk=e == null ? null : e.getKey();
    if (tk == null) {
      continue;
    }
    tv=e.getValue();
    if (opt == TYPE) {
      type(tk,tv,real);
    }
 else     if (opt == VERIFY) {
      verify(tk,tv,real,creator);
    }
 else     if (opt == UPDATE) {
      real.put(tk,tv);
    }
 else     if (opt == PUT) {
      real.put(tk,tv);
    }
 else {
      if (real.containsKey(tk)) {
        if (opt == REPLACE) {
          real.put(tk,tv);
        }
      }
 else {
        if (opt == INSERT) {
          real.put(tk,tv);
        }
        if (opt == ADD) {
          real.put(tk,tv);
        }
      }
    }
  }
  return real;
}

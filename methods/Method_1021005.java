/** 
 * copy new model with all attrs
 * @return
 */
public M copy(){
  M m=null;
  try {
    m=(M)_getUsefulClass().newInstance();
    m.put(_getAttrs());
  }
 catch (  Throwable e) {
    e.printStackTrace();
  }
  return m;
}

/** 
 * ???????key
 * @return
 */
@Override public String getPrefix(){
  String className=getClass().getSimpleName();
  return className + ":" + prefix;
}

/** 
 * @deprecated use {@link #getParameters()} instead.
 */
@Override @Deprecated public final Map<String,String> getParms(){
  Map<String,String> result=new HashMap<String,String>();
  for (  String key : this.parms.keySet()) {
    result.put(key,this.parms.get(key).get(0));
  }
  return result;
}

/** 
 * @return the previous value associated with the specified key, or<tt>null</tt> if there was no mapping for the key
 * @throws NullPointerException if the specified key or value is null
 */
public V replace(K key,V value){
  V retval=get(key);
  try {
    MethodCall call=new MethodCall(REPLACE_IF_EXISTS,key,value);
    disp.callRemoteMethods(null,call,call_options);
  }
 catch (  Exception e) {
    throw new RuntimeException("replace(" + key + ", " + value + ") failed",e);
  }
  return retval;
}

/** 
 * Unsafe version of  {@link #mName(AIString) mName}. 
 */
public static void nmName(long struct,AIString value){
  memCopy(value.address(),struct + AIMesh.MNAME,AIString.SIZEOF);
}

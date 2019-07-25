/** 
 * Makes a single-element instance.
 * @param type {@code non-null;} the element
 * @return {@code non-null;} an appropriately-constructed instance
 */
public static StdTypeList make(Type type){
  StdTypeList result=new StdTypeList(1);
  result.set(0,type);
  return result;
}

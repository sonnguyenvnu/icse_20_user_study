/** 
 * Makes a single-element instance.
 * @param spec {@code non-null;} the element
 * @return {@code non-null;} an appropriately-constructed instance
 */
public static RegisterSpecList make(RegisterSpec spec){
  RegisterSpecList result=new RegisterSpecList(1);
  result.set(0,spec);
  return result;
}

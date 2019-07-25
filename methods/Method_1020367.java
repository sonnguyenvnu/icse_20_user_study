/** 
 * Returns an instance for the given register number and type, with no variable info. This method is allowed to return shared instances (but doesn't necessarily do so).
 * @param reg {@code >= 0;} the register number
 * @param type {@code non-null;} the type (or possibly actual value) whichis loaded from or stored to the indicated register
 * @return {@code non-null;} an appropriately-constructed instance
 */
public static RegisterSpec make(int reg,TypeBearer type){
  return intern(reg,type,null);
}

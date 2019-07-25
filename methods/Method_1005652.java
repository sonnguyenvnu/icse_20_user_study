/** 
 * Checks to see if old namespace reg  {@code oldReg} interfereswith what currently maps to  {@code newReg}.
 * @param oldSpec {@code non-null;} old namespace register
 * @param newReg new namespace register
 * @return true if oldReg will interfere with newReg
 */
public boolean interferes(RegisterSpec oldSpec,int newReg){
  return interferes(oldSpec.getReg(),newReg,oldSpec.getCategory());
}

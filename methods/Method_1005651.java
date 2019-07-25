/** 
 * Checks to see if old namespace reg  {@code oldReg} interfereswith what currently maps to  {@code newReg}.
 * @param oldReg old namespace register
 * @param newReg new namespace register
 * @param category category of old namespace register
 * @return true if oldReg will interfere with newReg
 */
public boolean interferes(int oldReg,int newReg,int category){
  if (newReg >= newRegInterference.size()) {
    return false;
  }
 else {
    IntSet existing=newRegInterference.get(newReg);
    if (existing == null) {
      return false;
    }
 else     if (category == 1) {
      return existing.has(oldReg);
    }
 else {
      return existing.has(oldReg) || (interferes(oldReg,newReg + 1,category - 1));
    }
  }
}

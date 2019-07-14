/** 
 * Returns true if the parameter is covered by this mask.
 * @param sig The signature to test.
 * @return True if the parameter is covered by this mask
 */
public boolean covers(ApexOperationSignature sig){
  return visMask.contains(sig.visibility);
}

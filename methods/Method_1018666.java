/** 
 * @return length 
 */
public int length(){
  int rDataLen=rData == null ? 0 : rData.length();
  return name.length() + SHORT_SIZE_IN_BYTES * 3 + INT_SIZE_IN_BYTES + rDataLen;
}

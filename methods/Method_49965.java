/** 
 * Get X-Mms-Priority value.
 * @return the value
 */
public int getPriority(){
  return mPduHeaders.getOctet(PduHeaders.PRIORITY);
}

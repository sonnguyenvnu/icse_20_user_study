/** 
 * Get X-Mms-Content-Class Value.
 * @return the value
 */
public int getContentClass(){
  return mPduHeaders.getOctet(PduHeaders.CONTENT_CLASS);
}

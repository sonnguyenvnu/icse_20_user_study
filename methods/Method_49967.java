/** 
 * Get X-Mms-Report-Allowed field value.
 * @return the X-Mms-Report-Allowed value
 */
public int getReportAllowed(){
  return mPduHeaders.getOctet(PduHeaders.REPORT_ALLOWED);
}

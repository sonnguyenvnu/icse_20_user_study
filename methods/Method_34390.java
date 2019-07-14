/** 
 * Returns the Http Status used in the <i>OAuth2 Error Response</i> sent back to the caller. The default is 500.
 * @return the <code>Http Status</code> set on the <i>OAuth2 Error Response</i>
 */
@Override public int getHttpErrorCode(){
  return this.httpStatus;
}

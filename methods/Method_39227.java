/** 
 * Resets BCC addresses.
 */
public Email resetBcc(){
  this.bcc=EmailAddress.EMPTY_ARRAY;
  return _this();
}

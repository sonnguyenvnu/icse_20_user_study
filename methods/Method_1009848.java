/** 
 * Remove references to View components to avoid memory leak
 */
public void release(){
  mPhoneNumberInput.removeTextChangedListener(this);
  mPhoneNumberInput=null;
  mCountryCodeInput=null;
}

/** 
 * Clear phone number data
 */
public void reset(){
  mCurrentPhoneNumber=null;
  mPhoneNumberInput.setText("");
  mCountryCodeInput.setVisibility(View.GONE);
}

private boolean validatePhoneNumber(){
  String phoneNumber=mPhoneNumberField.getText().toString();
  if (TextUtils.isEmpty(phoneNumber)) {
    mPhoneNumberField.setError("Invalid phone number.");
    return false;
  }
  return true;
}

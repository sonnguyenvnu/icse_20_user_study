private void updateDropDownTextView(){
  if (dropDown != null) {
    if (currentPasswordType == 0) {
      dropDown.setText(LocaleController.getString("PasscodePIN",R.string.PasscodePIN));
    }
 else     if (currentPasswordType == 1) {
      dropDown.setText(LocaleController.getString("PasscodePassword",R.string.PasscodePassword));
    }
  }
  if (type == 1 && currentPasswordType == 0 || type == 2 && SharedConfig.passcodeType == 0) {
    InputFilter[] filterArray=new InputFilter[1];
    filterArray[0]=new InputFilter.LengthFilter(4);
    passwordEditText.setFilters(filterArray);
    passwordEditText.setInputType(InputType.TYPE_CLASS_PHONE);
    passwordEditText.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
  }
 else   if (type == 1 && currentPasswordType == 1 || type == 2 && SharedConfig.passcodeType == 1) {
    passwordEditText.setFilters(new InputFilter[0]);
    passwordEditText.setKeyListener(null);
    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
  }
  passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
}

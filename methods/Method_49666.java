public String getCodeAndLocality(){
  if (!localityValid) {
    return lastFullCode.getCode();
  }
  return mCodeTV.getText().toString() + " " + mLocalityTV.getText().toString();
}

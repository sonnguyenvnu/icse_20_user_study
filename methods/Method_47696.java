public boolean validate(){
  Resources res=getResources();
  String sValue=tvTargetValue.getText().toString();
  double value=Double.parseDouble(sValue);
  if (value <= 0) {
    tvTargetValue.setError(res.getString(R.string.validation_number_should_be_positive));
    return false;
  }
  return true;
}

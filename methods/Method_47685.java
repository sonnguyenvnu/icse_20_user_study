public boolean validate(){
  boolean valid=true;
  Resources res=getResources();
  String freqNum=tvNumerator.getText().toString();
  String freqDen=tvDenominator.getText().toString();
  if (freqDen.isEmpty()) {
    tvDenominator.setError(res.getString(R.string.validation_show_not_be_blank));
    valid=false;
  }
  if (freqNum.isEmpty()) {
    tvNumerator.setError(res.getString(R.string.validation_show_not_be_blank));
    valid=false;
  }
  if (!valid)   return false;
  int numerator=Integer.parseInt(freqNum);
  int denominator=Integer.parseInt(freqDen);
  if (numerator <= 0) {
    tvNumerator.setError(res.getString(R.string.validation_number_should_be_positive));
    valid=false;
  }
  if (numerator > denominator) {
    tvNumerator.setError(res.getString(R.string.validation_at_most_one_rep_per_day));
    valid=false;
  }
  return valid;
}

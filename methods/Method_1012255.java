/** 
 * ????????
 * @return
 */
public boolean validate(){
  if (mValidators == null || mValidators.isEmpty()) {
    return true;
  }
  CharSequence text=getText();
  boolean isEmpty=text.length() == 0;
  boolean isValid=true;
  for (  METValidator validator : mValidators) {
    isValid=isValid && validator.isValid(text,isEmpty);
    if (!isValid) {
      setError(validator.getErrorMessage());
      break;
    }
  }
  if (isValid) {
    setError(null);
  }
  postInvalidate();
  return isValid;
}

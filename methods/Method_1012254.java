/** 
 * Check all validators, sets the error text if not <p/> NOTE: this stops at the first validator to report invalid.
 * @return True if all validators pass, false if not
 */
public boolean validate(){
  if (validators == null || validators.isEmpty()) {
    return true;
  }
  CharSequence text=getText();
  boolean isEmpty=text.length() == 0;
  boolean isValid=true;
  for (  METValidator validator : validators) {
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

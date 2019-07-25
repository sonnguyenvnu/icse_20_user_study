/** 
 * check if the main text matches the regex, and set the error text if not.
 * @return true if it matches the regex, false if not.
 * @deprecated use the new validator interface to add your own custom validator
 */
@Deprecated public boolean validate(String regex,CharSequence errorText){
  boolean isValid=isValid(regex);
  if (!isValid) {
    setError(errorText);
  }
  postInvalidate();
  return isValid;
}

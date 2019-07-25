/** 
 * Update the onError state of this component
 * @return true if valid (the inverse value of onError)
 */
public boolean validate(){
  removeError();
  updateCounterText(true);
  if (onError) {
    setError(null,false);
  }
  return !onError;
}

/** 
 * Make a JSONException to signal a syntax error.
 * @param message The error message.
 * @param causedBy The throwable that caused the error.
 * @return  A JSONException object, suitable for throwing
 */
public JSONException syntaxError(String message,Throwable causedBy){
  return new JSONException(message + this.toString(),causedBy);
}

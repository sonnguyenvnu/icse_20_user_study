/** 
 * Add new error message to the  {@link #getErrors() errors list}. If errors are not collected error, message is ignored.
 */
public void addError(final String message){
  if (config.collectErrors) {
    if (errors == null) {
      errors=new ArrayList<>();
    }
    errors.add(message);
  }
}

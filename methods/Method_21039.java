/** 
 * Returns the first error message available, or `null` if there are none.
 */
public @Nullable String errorMessage(){
  if (errorMessages() == null) {
    return null;
  }
 else {
    return ListUtils.first(errorMessages());
  }
}

/** 
 * We can't show any violations if we don't have any visible columns.
 * @see PropertySource#dysfunctionReason()
 */
@Override public String dysfunctionReason(){
  return activeColumns().isEmpty() ? "No columns selected" : null;
}

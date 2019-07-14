/** 
 * @see PropertySource#dysfunctionReason()
 */
@Override public String dysfunctionReason(){
  return hasDissallowedTerms() ? null : "No disallowed terms specified";
}

/** 
 * @see PropertySource#dysfunctionReason()
 */
@Override public String dysfunctionReason(){
  return checksNothing() ? "No packages or classes specified" : null;
}

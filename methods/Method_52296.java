/** 
 * @see PropertySource#dysfunctionReason()
 */
@Override public String dysfunctionReason(){
  return checksNothing() ? "All loop types are ignored" : null;
}

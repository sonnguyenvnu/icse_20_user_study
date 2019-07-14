/** 
 * Returns the  {@link APIVersion} value of the specified option.
 * @param option the option to query
 */
@Nullable public static APIVersion apiParseVersion(Configuration<?> option){
  APIVersion version;
  Object state=option.get();
  if (state instanceof String) {
    version=apiParseVersion((String)state,null);
  }
 else   if (state instanceof APIVersion) {
    version=(APIVersion)state;
  }
 else {
    version=null;
  }
  return version;
}

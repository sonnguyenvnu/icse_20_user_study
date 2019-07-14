/** 
 * @see PropertySource#dysfunctionReason()
 */
@Override public String dysfunctionReason(){
  File file=getProperty(EXCEPTION_FILE_DESCRIPTOR);
  if (file != null) {
    String issue=checkFile(file);
    if (issue != null) {
      return issue;
    }
    String ignores=getProperty(EXCEPTION_LIST_DESCRIPTOR);
    if (StringUtils.isNotBlank(ignores)) {
      return "Cannot reference external file AND local values";
    }
  }
  return null;
}

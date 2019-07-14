/** 
 * passing "/foo/bar" as treePath will result:<br> 1. load [twitter4j.]restBaseURL<br> 2. override the value with foo.[twitter4j.]restBaseURL<br> 3. override the value with foo.bar.[twitter4j.]restBaseURL<br>
 * @param props    properties to be loaded
 * @param treePath the path
 */
private void setFieldsWithTreePath(Properties props,String treePath){
  setFieldsWithPrefix(props,"");
  String[] splitArray=treePath.split("/");
  String prefix=null;
  for (  String split : splitArray) {
    if (!"".equals(split)) {
      if (null == prefix) {
        prefix=split + ".";
      }
 else {
        prefix+=split + ".";
      }
      setFieldsWithPrefix(props,prefix);
    }
  }
}

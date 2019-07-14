/** 
 * Post process final content. Required for <code>RESOURCE_ONLY</code> strategy.
 */
public char[] postProcess(char[] content){
  content=jsBundleAction.replaceBundleId(content);
  content=cssBundleAction.replaceBundleId(content);
  return content;
}

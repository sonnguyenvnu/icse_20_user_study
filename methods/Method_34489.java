/** 
 * {@inheritDoc}
 */
@Override public String getActionName(){
  return StringUtils.isNotEmpty(originalMetaHolder.getHystrixCommand().commandKey()) ? originalMetaHolder.getHystrixCommand().commandKey() : originalMetaHolder.getDefaultCommandKey();
}

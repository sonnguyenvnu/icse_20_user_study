/** 
 * Creates new  {@link Props} with default configuration.Empty props will be ignored, and missing macros will be resolved as empty string.
 */
protected Props createProps(){
  final Props props=new Props();
  props.setSkipEmptyProps(true);
  props.setIgnoreMissingMacros(true);
  return props;
}

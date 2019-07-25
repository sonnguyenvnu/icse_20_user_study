/** 
 * {@inheritDoc}
 */
@Override public boolean validate(List<String> warnings){
  this.warnings=warnings;
  if (StringUtility.stringHasValue(getContext().getTargetRuntime()) && "MyBatis3".equalsIgnoreCase(getContext().getTargetRuntime()) == false) {
    warnings.add("itfsw:??" + this.getClass().getTypeName() + "????targetRuntime???MyBatis3?");
    return false;
  }
  return true;
}

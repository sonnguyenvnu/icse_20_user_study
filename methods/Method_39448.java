/** 
 * Resolves active profiles from special property. This property can be only a base property! If default active property is not defined, nothing happens. Otherwise, it will replace currently active profiles.
 */
protected void resolveActiveProfiles(){
  if (activeProfilesProp == null) {
    activeProfiles=null;
    return;
  }
  final PropsEntry pv=data.getBaseProperty(activeProfilesProp);
  if (pv == null) {
    return;
  }
  final String value=pv.getValue();
  if (StringUtil.isBlank(value)) {
    activeProfiles=null;
    return;
  }
  activeProfiles=StringUtil.splitc(value,',');
  StringUtil.trimAll(activeProfiles);
}

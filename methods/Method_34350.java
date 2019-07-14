/** 
 * Default value for authorities if an Authentication is being created and the input has no data for authorities. Note that unless this property is set, the default Authentication created by  {@link #extractAuthentication(Map)}will be unauthenticated.
 * @param defaultAuthorities the defaultAuthorities to set. Default null.
 */
public void setDefaultAuthorities(String[] defaultAuthorities){
  this.defaultAuthorities=AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.arrayToCommaDelimitedString(defaultAuthorities));
}

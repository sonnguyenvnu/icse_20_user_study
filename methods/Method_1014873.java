/** 
 * Updates by domain
 * @param domain domain
 * @return DOMAIN
 */
@Override public DOMAIN update(DOMAIN domain){
  Assert.notNull(domain,domainName + " data must not be null");
  return repository.saveAndFlush(domain);
}

/** 
 * ????user Realm??
 * @param retryLimitHashedCredentialsMatcher ?????
 * @return ???Realm
 */
@Bean(name="userRealm") public OperatorRealm operatorRealm(@Qualifier("credentialsMatcher") RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher){
  OperatorRealm operatorRealm=new OperatorRealm();
  operatorRealm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher);
  operatorRealm.setCachingEnabled(false);
  return operatorRealm;
}

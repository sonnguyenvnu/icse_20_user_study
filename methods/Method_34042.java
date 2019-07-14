/** 
 * Initialze these token services. If no random generator is set, one will be created.
 */
public void afterPropertiesSet() throws Exception {
  if (random == null) {
    random=new SecureRandom();
  }
}

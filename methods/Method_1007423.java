/** 
 * Plugin initialization properties (from Hibernate3JPAHelper or SessionFactoryProxy).
 * @param version the version
 * @param hibernateEjb the hibernate ejb
 */
public void init(String version,Boolean hibernateEjb){
  LOGGER.info("Hibernate3 JPA plugin initialized - Hibernate Core version '{}'",version);
  this.hibernateEjb=hibernateEjb;
}

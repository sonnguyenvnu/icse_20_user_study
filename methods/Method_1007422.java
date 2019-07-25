/** 
 * Plugin initialization properties (from HibernatePersistenceHelper or SessionFactoryProxy)
 */
public void init(String version,Boolean hibernateEjb){
  LOGGER.info("Hibernate plugin initialized - Hibernate Core version '{}'",version);
  this.hibernateEjb=hibernateEjb;
}

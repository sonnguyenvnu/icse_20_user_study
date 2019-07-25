/** 
 * Adapts log4j logger to our extended facility These two methods we need for a migration script
 */
public static Logger wrap(org.apache.log4j.Logger logger){
  return new Log4jLogger(logger);
}

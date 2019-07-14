/** 
 * Initializes the logging.
 * @param level The minimum level to log at.
 */
static void initLogging(Level level){
  LogFactory.setFallbackLogCreator(new ConsoleLogCreator(level));
  LOG=LogFactory.getLog(Main.class);
}

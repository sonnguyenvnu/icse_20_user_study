/** 
 * Assimilates an existing JUL log manager. It resets the manager configuration, and adds a bridge handler to the root logger. Messages are redirected to the specified Log4j logger repository.
 * @param loggerRepository the Log4j logger repository to use to redirect messages
 */
public static void assimilate(LoggerRepository loggerRepository){
  LogManager.getLogManager().reset();
  boolean withExtendedLocationInfos=Boolean.getBoolean(USE_EXTENDED_LOCATION_INFO_PROPERTYNAME);
  Logger rootLogger=Logger.getLogger("");
  rootLogger.setLevel(JULBridgeLevelConverter.fromLog4jLevel(loggerRepository.getThreshold()));
  rootLogger.addHandler(new JULBridgeHandler(loggerRepository,withExtendedLocationInfos));
}

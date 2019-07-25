/** 
 * Initializes SettingsManager.
 */
public static synchronized void initialize(){
  if (!initialized) {
    initialized=true;
    System.setProperty("org.slf4j.simpleLogger.logFile","System.out");
    System.setProperty("org.slf4j.simpleLogger.levelInBrackets","true");
  }
}

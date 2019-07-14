/** 
 * Toggles a Yoga setting on whether to print debug logs to adb.
 * @param enable whether to print logs or not
 */
public static void setPrintYogaDebugLogs(boolean enable){
synchronized (sYogaConfigLock) {
    sYogaConfig.setPrintTreeFlag(enable);
  }
}

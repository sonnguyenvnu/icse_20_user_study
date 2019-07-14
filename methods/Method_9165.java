public static int getDataSavingDefault(){
  boolean low=DownloadController.getInstance(0).lowPreset.lessCallData, medium=DownloadController.getInstance(0).mediumPreset.lessCallData, high=DownloadController.getInstance(0).highPreset.lessCallData;
  if (!low && !medium && !high) {
    return VoIPController.DATA_SAVING_NEVER;
  }
 else   if (low && !medium && !high) {
    return VoIPController.DATA_SAVING_ROAMING;
  }
 else   if (low && medium && !high) {
    return VoIPController.DATA_SAVING_MOBILE;
  }
 else   if (low && medium && high) {
    return VoIPController.DATA_SAVING_ALWAYS;
  }
  if (BuildVars.LOGS_ENABLED)   FileLog.w("Invalid call data saving preset configuration: " + low + "/" + medium + "/" + high);
  return VoIPController.DATA_SAVING_NEVER;
}

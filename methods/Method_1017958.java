public String build(){
  if (tdchOperationType == null) {
    return EMPTY_STRING;
  }
  if (tdchOperationType.equals(TdchOperationType.TDCH_EXPORT) && commonExportToolJobType.equals("hive")) {
    return buildTdchExportHiveToTeradataCommand();
  }
 else   if (tdchOperationType.equals(TdchOperationType.TDCH_IMPORT)) {
    logger.warn("TDCH Import not yet implemented");
    return EMPTY_STRING;
  }
  logger.error("Unsupported TDCH operation (Operation Type: {}, Job Type: {})",new Object[]{tdchOperationType,commonExportToolJobType});
  return EMPTY_STRING;
}

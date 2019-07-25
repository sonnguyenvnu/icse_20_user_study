/** 
 * Constructs  {@link LocalDateTimeInfo} with GAP status.
 */
public static LocalDateTimeInfo gap(long resolvedTimestamp){
  LocalDateTimeInfo localDateTimeInfo=new LocalDateTimeInfo();
  localDateTimeInfo.resolvedStatus=LocalDateTimeAmbiguityStatus.GAP;
  localDateTimeInfo.resolvedTimestamp=resolvedTimestamp;
  return localDateTimeInfo;
}

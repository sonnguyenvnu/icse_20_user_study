/** 
 * Constructs  {@link LocalDateTimeInfo} with UNAMBIGUOUS status.
 */
public static LocalDateTimeInfo unambiguous(long resolvedTimestamp){
  LocalDateTimeInfo localDateTimeInfo=new LocalDateTimeInfo();
  localDateTimeInfo.resolvedStatus=LocalDateTimeAmbiguityStatus.UNAMBIGUOUS;
  localDateTimeInfo.resolvedTimestamp=resolvedTimestamp;
  return localDateTimeInfo;
}

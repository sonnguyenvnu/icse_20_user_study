/** 
 * @throws DateTimeParseException
 */
public static ZonedDateTime parseDoubanDateTime(String doubanDateTime){
  return ZonedDateTime.of(LocalDateTime.parse(doubanDateTime,DOUBAN_DATE_TIME_FORMATTER),DOUBAN_ZONE_ID).withZoneSameInstant(ZoneId.systemDefault());
}

/** 
 * @throws DateTimeParseException
 */
public static YearMonth parseDoubanYearMonth(String doubanDate){
  return YearMonth.parse(doubanDate,DOUBAN_YEAR_MONTH_FORMATTER);
}

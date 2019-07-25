@Override public String format(String month){
  if (month == null) {
    return "";
  }
  Optional<Month> parsedMonth=Month.getMonthByShortName(month);
  return parsedMonth.map(Month::getTwoDigitNumber).orElse(month.toLowerCase(Locale.ROOT));
}

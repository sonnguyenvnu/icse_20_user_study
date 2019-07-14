/** 
 * Obtain a Strategy given a field from a SimpleDateFormat pattern
 * @param formatField      A sub-sequence of the SimpleDateFormat pattern
 * @param definingCalendar The calendar to obtain the short and long values
 * @return The Strategy that will handle parsing for the field
 */
private Strategy getStrategy(final String formatField,final Calendar definingCalendar){
switch (formatField.charAt(0)) {
case '\'':
    if (formatField.length() > 2) {
      return new CopyQuotedStrategy(formatField.substring(1,formatField.length() - 1));
    }
default :
  return new CopyQuotedStrategy(formatField);
case 'D':
return DAY_OF_YEAR_STRATEGY;
case 'E':
return getLocaleSpecificStrategy(Calendar.DAY_OF_WEEK,definingCalendar);
case 'F':
return DAY_OF_WEEK_IN_MONTH_STRATEGY;
case 'G':
return getLocaleSpecificStrategy(Calendar.ERA,definingCalendar);
case 'H':
return MODULO_HOUR_OF_DAY_STRATEGY;
case 'K':
return HOUR_STRATEGY;
case 'M':
case 'L':
return formatField.length() >= 3 ? getLocaleSpecificStrategy(Calendar.MONTH,definingCalendar) : NUMBER_MONTH_STRATEGY;
case 'S':
return MILLISECOND_STRATEGY;
case 'W':
return WEEK_OF_MONTH_STRATEGY;
case 'a':
return getLocaleSpecificStrategy(Calendar.AM_PM,definingCalendar);
case 'd':
return DAY_OF_MONTH_STRATEGY;
case 'h':
return MODULO_HOUR_STRATEGY;
case 'k':
return HOUR_OF_DAY_STRATEGY;
case 'm':
return MINUTE_STRATEGY;
case 's':
return SECOND_STRATEGY;
case 'w':
return WEEK_OF_YEAR_STRATEGY;
case 'y':
return formatField.length() > 2 ? LITERAL_YEAR_STRATEGY : ABBREVIATED_YEAR_STRATEGY;
case 'Z':
case 'z':
return getLocaleSpecificStrategy(Calendar.ZONE_OFFSET,definingCalendar);
}
}

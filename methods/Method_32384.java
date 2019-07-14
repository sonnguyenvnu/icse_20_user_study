/** 
 * Returns a formatter that outputs only those fields specified. <p> This method examines the fields provided and returns an ISO-style formatter that best fits. This can be useful for outputting less-common ISO styles, such as YearMonth (YYYY-MM) or MonthDay (--MM-DD). <p> The list provided may have overlapping fields, such as dayOfWeek and dayOfMonth. In this case, the style is chosen based on the following list, thus in the example, the calendar style is chosen as dayOfMonth is higher in priority than dayOfWeek: <ul> <li>monthOfYear - calendar date style <li>dayOfYear - ordinal date style <li>weekOfWeekYear - week date style <li>dayOfMonth - calendar date style <li>dayOfWeek - week date style <li>year <li>weekyear </ul> The supported formats are: <pre> Extended      Basic       Fields 2005-03-25    20050325    year/monthOfYear/dayOfMonth 2005-03       2005-03     year/monthOfYear 2005--25      2005--25    year/dayOfMonth  2005          2005        year --03-25       --0325      monthOfYear/dayOfMonth --03          --03        monthOfYear ---03         ---03       dayOfMonth 2005-084      2005084     year/dayOfYear -084          -084        dayOfYear 2005-W12-5    2005W125    weekyear/weekOfWeekyear/dayOfWeek 2005-W-5      2005W-5     weekyear/dayOfWeek  2005-W12      2005W12     weekyear/weekOfWeekyear -W12-5        -W125       weekOfWeekyear/dayOfWeek -W12          -W12        weekOfWeekyear -W-5          -W-5        dayOfWeek 10:20:30.040  102030.040  hour/minute/second/milli 10:20:30      102030      hour/minute/second 10:20         1020        hour/minute 10            10          hour -20:30.040    -2030.040   minute/second/milli -20:30        -2030       minute/second -20           -20         minute --30.040      --30.040    second/milli --30          --30        second ---.040       ---.040     milli  10-30.040     10-30.040   hour/second/milli  10:20-.040    1020-.040   hour/minute/milli  10-30         10-30       hour/second  10--.040      10--.040    hour/milli  -20-.040      -20-.040    minute/milli  plus datetime formats like {date}T{time} </pre> * indicates that this is not an official ISO format and can be excluded by passing in <code>strictISO</code> as <code>true</code>. <p> This method can side effect the input collection of fields. If the input collection is modifiable, then each field that was added to the formatter will be removed from the collection, including any duplicates. If the input collection is unmodifiable then no side effect occurs. <p> This side effect processing is useful if you need to know whether all the fields were converted into the formatter or not. To achieve this, pass in a modifiable list, and check that it is empty on exit.
 * @param fields  the fields to get a formatter for, not null,updated by the method call unless unmodifiable, removing those fields built in the formatter
 * @param extended  true to use the extended format (with separators)
 * @param strictISO  true to stick exactly to ISO8601, false to include additional formats
 * @return a suitable formatter
 * @throws IllegalArgumentException if there is no format for the fields
 * @since 1.1
 */
public static DateTimeFormatter forFields(Collection<DateTimeFieldType> fields,boolean extended,boolean strictISO){
  if (fields == null || fields.size() == 0) {
    throw new IllegalArgumentException("The fields must not be null or empty");
  }
  Set<DateTimeFieldType> workingFields=new HashSet<DateTimeFieldType>(fields);
  int inputSize=workingFields.size();
  boolean reducedPrec=false;
  DateTimeFormatterBuilder bld=new DateTimeFormatterBuilder();
  if (workingFields.contains(DateTimeFieldType.monthOfYear())) {
    reducedPrec=dateByMonth(bld,workingFields,extended,strictISO);
  }
 else   if (workingFields.contains(DateTimeFieldType.dayOfYear())) {
    reducedPrec=dateByOrdinal(bld,workingFields,extended,strictISO);
  }
 else   if (workingFields.contains(DateTimeFieldType.weekOfWeekyear())) {
    reducedPrec=dateByWeek(bld,workingFields,extended,strictISO);
  }
 else   if (workingFields.contains(DateTimeFieldType.dayOfMonth())) {
    reducedPrec=dateByMonth(bld,workingFields,extended,strictISO);
  }
 else   if (workingFields.contains(DateTimeFieldType.dayOfWeek())) {
    reducedPrec=dateByWeek(bld,workingFields,extended,strictISO);
  }
 else   if (workingFields.remove(DateTimeFieldType.year())) {
    bld.append(Constants.ye);
    reducedPrec=true;
  }
 else   if (workingFields.remove(DateTimeFieldType.weekyear())) {
    bld.append(Constants.we);
    reducedPrec=true;
  }
  boolean datePresent=(workingFields.size() < inputSize);
  time(bld,workingFields,extended,strictISO,reducedPrec,datePresent);
  if (bld.canBuildFormatter() == false) {
    throw new IllegalArgumentException("No valid format for fields: " + fields);
  }
  try {
    fields.retainAll(workingFields);
  }
 catch (  UnsupportedOperationException ex) {
  }
  return bld.toFormatter();
}

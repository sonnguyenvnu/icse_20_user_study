private FastDateFormat createFormatter(Locale locale,String format,String defaultFormat){
  if (format == null || format.length() == 0) {
    format=defaultFormat;
  }
  FastDateFormat formatter;
  try {
    formatter=FastDateFormat.getInstance(format,locale);
  }
 catch (  Exception e) {
    format=defaultFormat;
    formatter=FastDateFormat.getInstance(format,locale);
  }
  return formatter;
}

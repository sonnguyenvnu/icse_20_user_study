private DateFormat _force(DateFormat df,TimeZone tz){
  if (df instanceof StdDateFormat) {
    return ((StdDateFormat)df).withTimeZone(tz);
  }
  df=(DateFormat)df.clone();
  df.setTimeZone(tz);
  return df;
}

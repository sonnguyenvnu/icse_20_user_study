@Override public Object apply(Date context,Options options) throws IOException {
  String format=options.hash("format",null);
  String offset=options.hash("offset",null);
  String timezone=options.hash("timezone",null);
  Date date=context != null ? context : new Date();
  if (offset != null) {
    date=new DateOffset(offset).shift(date);
  }
  return new RenderableDate(date,format,timezone);
}

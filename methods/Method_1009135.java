@Override public final void validate(final ProcessingReport report,final MessageBundle bundle,final FullData data) throws ProcessingException {
  final DateTimeFormatter formatter=getFormatter();
  final String value=data.getInstance().getNode().textValue();
  try {
    formatter.parseDateTime(value);
  }
 catch (  IllegalArgumentException ignored) {
    report.error(newMsg(data,bundle,"err.format.invalidDate").putArgument("value",value).putArgument("expected",format));
  }
}

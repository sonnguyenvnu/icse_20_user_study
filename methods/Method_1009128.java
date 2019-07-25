@Override public void validate(final ProcessingReport report,final MessageBundle bundle,final FullData data) throws ProcessingException {
  final String value=data.getInstance().getNode().textValue();
  try {
    FORMATTER.parseDateTime(value);
  }
 catch (  IllegalArgumentException ignored) {
    report.error(newMsg(data,bundle,"err.format.invalidDate").putArgument("value",value).putArgument("expected",FORMATS));
  }
}

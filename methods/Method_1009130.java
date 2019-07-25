@Override public void validate(final ProcessingReport report,final MessageBundle bundle,final FullData data) throws ProcessingException {
  final String value=data.getInstance().getNode().textValue();
  try {
    FORMATTER.parseDateTime(value);
    final String secFracsAndOffset=value.substring("yyyy-MM-ddTHH:mm:ss".length());
    final String offset;
    if (!secFracsAndOffset.startsWith(".")) {
      offset=secFracsAndOffset;
    }
 else {
      if (secFracsAndOffset.contains("Z")) {
        offset=secFracsAndOffset.substring(secFracsAndOffset.indexOf("Z"));
      }
 else       if (secFracsAndOffset.contains("+")) {
        offset=secFracsAndOffset.substring(secFracsAndOffset.indexOf("+"));
      }
 else {
        offset=secFracsAndOffset.substring(secFracsAndOffset.indexOf("-"));
      }
    }
    if (!isOffSetStrictRFC3339(offset)) {
      throw new IllegalArgumentException();
    }
  }
 catch (  IllegalArgumentException ignored) {
    report.error(newMsg(data,bundle,"err.format.invalidDate").putArgument("value",value).putArgument("expected",RFC3339_FORMATS));
  }
}

@Override public void validate(final ProcessingReport report,final MessageBundle bundle,final FullData data) throws ProcessingException {
  final String value=data.getInstance().getNode().textValue();
  try {
    new URI(value);
  }
 catch (  URISyntaxException ignored) {
    report.error(newMsg(data,bundle,"err.format.invalidURI").putArgument("value",value));
  }
}

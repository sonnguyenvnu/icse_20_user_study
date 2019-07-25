@Override public void validate(final ProcessingReport report,final MessageBundle bundle,final FullData data) throws ProcessingException {
  final String input=data.getInstance().getNode().textValue();
  try {
    UUID.fromString(input);
  }
 catch (  IllegalArgumentException ignored) {
    report.error(newMsg(data,bundle,"err.format.UUID.invalid").putArgument("value",input));
  }
}

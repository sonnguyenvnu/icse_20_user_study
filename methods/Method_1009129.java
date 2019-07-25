@Override public void validate(final ProcessingReport report,final MessageBundle bundle,final FullData data) throws ProcessingException {
  final String value=data.getInstance().getNode().textValue();
  try {
    new InternetAddress(value,true);
  }
 catch (  AddressException ignored) {
    report.error(newMsg(data,bundle,"err.format.invalidEmail").putArgument("value",value));
  }
}

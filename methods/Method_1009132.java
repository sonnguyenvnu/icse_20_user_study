@Override public void validate(final ProcessingReport report,final MessageBundle bundle,final FullData data) throws ProcessingException {
  final String input=data.getInstance().getNode().textValue();
  try {
    if (input.startsWith("+"))     PARSER.parse(input,"ZZ");
 else     PARSER.parse(input,"FR");
  }
 catch (  NumberParseException ignored) {
    report.error(newMsg(data,bundle,"err.format.invalidPhoneNumber").putArgument("value",input));
  }
}

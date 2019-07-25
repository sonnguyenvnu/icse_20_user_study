@Override public void validate(final ProcessingReport report,final MessageBundle bundle,final FullData data) throws ProcessingException {
  final String input=data.getInstance().getNode().textValue();
  if (input.length() % 4 != 0) {
    report.error(newMsg(data,bundle,"err.format.base64.badLength").putArgument("length",input.length()));
    return;
  }
  final int index=NOT_BASE64.indexIn(PATTERN.matcher(input).replaceFirst(""));
  if (index == -1)   return;
  report.error(newMsg(data,bundle,"err.format.base64.illegalChars").putArgument("character",Character.toString(input.charAt(index))).putArgument("index",index));
}

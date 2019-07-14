public static String asContent(final HttpMessage message){
  if (hasContent(message)) {
    return StringUtil.NEWLINE + StringUtil.NEWLINE + contentForDump(message);
  }
  return "";
}

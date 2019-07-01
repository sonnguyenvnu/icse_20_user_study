/** 
 * Handle any command line token.
 * @param token the command line token to handle
 * @throws ParseException
 */
private void _XXXXX_(final String token) throws ParseException {
  currentToken=token;
  if (skipParsing) {
    cmd.addArg(token);
  }
 else   if ("--".equals(token)) {
    skipParsing=true;
  }
 else   if (currentOption != null && currentOption.acceptsArg() && isArgument(token)) {
    currentOption.addValueForProcessing(Util.stripLeadingAndTrailingQuotes(token));
  }
 else   if (token.startsWith("--")) {
    handleLongOption(token);
  }
 else   if (token.startsWith("-") && !"-".equals(token)) {
    handleShortAndLongOption(token);
  }
 else {
    handleUnknownToken(token);
  }
  if (currentOption != null && !currentOption.acceptsArg()) {
    currentOption=null;
  }
}
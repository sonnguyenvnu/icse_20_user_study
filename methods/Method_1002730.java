/** 
 * Parses a media type from its string representation.
 * @throws IllegalArgumentException if the input is not parsable
 */
public static MediaType parse(String input){
  checkNotNull(input);
  final MediaType wellKnown=KnownTypesByString.get(input);
  if (wellKnown != null) {
    return wellKnown;
  }
  Tokenizer tokenizer=new Tokenizer(input);
  try {
    String type=tokenizer.consumeToken(TOKEN_MATCHER);
    tokenizer.consumeCharacter('/');
    String subtype=tokenizer.consumeToken(TOKEN_MATCHER);
    ImmutableListMultimap.Builder<String,String> parameters=ImmutableListMultimap.builder();
    while (tokenizer.hasMore()) {
      tokenizer.consumeTokenIfPresent(LINEAR_WHITE_SPACE);
      tokenizer.consumeCharacter(';');
      tokenizer.consumeTokenIfPresent(LINEAR_WHITE_SPACE);
      String attribute=tokenizer.consumeToken(TOKEN_MATCHER);
      tokenizer.consumeCharacter('=');
      final String value;
      if ('"' == tokenizer.previewChar()) {
        tokenizer.consumeCharacter('"');
        StringBuilder valueBuilder=new StringBuilder();
        while ('"' != tokenizer.previewChar()) {
          if ('\\' == tokenizer.previewChar()) {
            tokenizer.consumeCharacter('\\');
            valueBuilder.append(tokenizer.consumeCharacter(ascii()));
          }
 else {
            valueBuilder.append(tokenizer.consumeToken(QUOTED_TEXT_MATCHER));
          }
        }
        value=valueBuilder.toString();
        tokenizer.consumeCharacter('"');
      }
 else {
        value=tokenizer.consumeToken(TOKEN_MATCHER);
      }
      parameters.put(attribute,value);
    }
    return create(type,subtype,parameters.build());
  }
 catch (  IllegalStateException e) {
    throw new IllegalArgumentException("Could not parse '" + input + '\'',e);
  }
}

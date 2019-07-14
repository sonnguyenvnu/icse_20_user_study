private static String formatConstantsGL(String input,String prefix){
  StringBuilder builder=new StringBuilder(input.length());
  Matcher blockMatcher=BLOCK_PATTERN.matcher(input);
  int blockCount=0;
  while (blockMatcher.find()) {
    if (0 < blockCount++) {
      builder.append('\n');
    }
    String description=blockMatcher.group(1);
    if (description == null) {
      description=blockMatcher.group(2) + '.';
    }
    description=CODE_CLEANUP.matcher(COMMENT_CLEANUP.matcher(description).replaceAll(" ").trim()).replaceAll("{@code $1}");
    builder.append("    IntConstant(\n");
    if (description.length() <= 160 - (4 + 4 + 2 + 1)) {
      builder.append("        \"");
      builder.append(description);
      builder.append('\"');
    }
 else {
      builder.append("        \"\"\"\n");
      builder.append("        ");
      String[] tokens=TOKEN_SPLIT.split(description);
      int MAX_LINE_LENGTH=160 - (4 + 4);
      int lineLength=0;
      for (      String token : tokens) {
        lineLength+=token.length();
        if (token.length() < lineLength) {
          if (MAX_LINE_LENGTH < 1 + lineLength) {
            builder.append("\n        ");
            lineLength=token.length();
          }
 else {
            builder.append(' ');
            lineLength++;
          }
        }
        builder.append(token);
      }
      builder.append("\n        \"\"\"");
    }
    builder.append(",\n\n");
    Matcher constantMatcher=CONSTANT_PATTERN.matcher(blockMatcher.group(3));
    int constCount=0;
    while (constantMatcher.find()) {
      if (0 < constCount++) {
        builder.append(",\n");
      }
      builder.append("        \"");
      builder.append(strip(constantMatcher.group(1),prefix + '_'));
      String value=constantMatcher.group(2);
      try {
        String intValue=value.endsWith("L") ? value.substring(0,value.length() - (value.endsWith("UL") ? 2 : 1)) : value;
        validateInteger(intValue);
        if (intValue.startsWith("0x")) {
          builder.append("\"..");
          builder.append(intValue);
        }
 else {
          builder.append("\"..\"");
          builder.append(intValue);
          builder.append("\"");
        }
      }
 catch (      NumberFormatException e) {
        builder.append("\"..\"");
        builder.append(value.charAt(0) == '(' ? value.substring(1,value.length() - 1) : value);
        builder.append("\"");
      }
    }
    builder.append("\n    )\n");
  }
  return builder.toString();
}

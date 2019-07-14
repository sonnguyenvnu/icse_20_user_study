private static String formatFunctions(String input,String prefix,boolean prefixTypes){
  StringBuilder builder=new StringBuilder(input.length());
  Matcher funcMatcher=FUNCTION_PATTERN.matcher(input);
  int funcCount=0;
  while (funcMatcher.find()) {
    if (0 < funcCount++) {
      builder.append("\n\n");
    }
    String function=funcMatcher.group();
    Matcher paramMatcher=PARAM_PATTERN.matcher(function);
    int paramCount=-1;
    while (paramMatcher.find()) {
      if (paramCount == -1) {
        builder.append("    ");
        formatType(paramMatcher,builder,prefixTypes ? prefix : "");
        if (paramMatcher.group(1) != null || paramMatcher.group(4) != null) {
          builder.append(".const");
        }
        writePointer(builder,paramMatcher);
        builder.append("(\n");
        builder.append("        \"");
        builder.append(strip(paramMatcher.group(6),prefix));
        builder.append("\",\n");
        builder.append("        \"\"");
        paramCount=0;
        if ("void".equals(funcMatcher.group(1))) {
          break;
        }
      }
 else {
        builder.append(",\n");
        if (paramCount++ == 0) {
          builder.append('\n');
        }
        builder.append("        ");
        formatType(paramMatcher,builder,prefixTypes ? prefix : "");
        if (paramMatcher.group(1) != null || paramMatcher.group(4) != null) {
          builder.append(".const");
        }
        writePointer(builder,paramMatcher);
        builder.append("(\"");
        builder.append(paramMatcher.group(6));
        builder.append("\", \"\")");
      }
    }
    builder.append("\n    )");
  }
  return builder.toString();
}

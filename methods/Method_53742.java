private static void formatType(Matcher paramMatcher,StringBuilder builder,String prefix){
  if (paramMatcher.group(2) != null) {
    builder.append(paramMatcher.group(2).trim() + "_");
  }
  String type=paramMatcher.group(3);
  if (!prefix.isEmpty() && !type.substring(0,prefix.length()).equalsIgnoreCase(prefix)) {
    builder.append(prefix);
  }
  builder.append(type.replace(' ','_'));
}

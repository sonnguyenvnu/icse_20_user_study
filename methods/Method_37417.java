@Override public void accept(final String... args){
  assertConfigurationIsValid();
  boolean dontParseOptionsAnyMore=false;
  int i;
  int paramsIndex=0;
  for (i=0; i < args.length; i++) {
    final String arg=args[i];
    if (arg.isEmpty()) {
      continue;
    }
    final String value=(i + 1 < args.length) ? args[i + 1] : null;
    if (arg.equals("--")) {
      dontParseOptionsAnyMore=true;
      continue;
    }
    if (!dontParseOptionsAnyMore) {
      if (arg.startsWith("--")) {
        final String argLongName=arg.substring(2);
        consumeOptionWithLongName(argLongName,value);
        args[i]=null;
        continue;
      }
      if (arg.startsWith("-")) {
        final String argShortName=arg.substring(1);
        if (argShortName.length() > 1 && argShortName.charAt(1) != '=') {
          final char[] allShortNames=argShortName.toCharArray();
          for (          final char c : allShortNames) {
            final String argName=String.valueOf(c);
            consumeOptionWithShortNameAndNoArguments(argName);
          }
          args[i]=null;
          continue;
        }
        final boolean valueConsumed=consumeOptionWithShortName(argShortName,value);
        args[i]=null;
        if (valueConsumed) {
          i++;
          args[i]=null;
        }
        continue;
      }
    }
    if (paramsIndex == params.size()) {
      break;
    }
    final Param param=params.get(paramsIndex++);
    final List<String> paramArguments=new ArrayList<>();
    int from=0;
    final int to=param.required + param.optional;
    for (; from < to; from++, i++) {
      final String paramValue=(i < args.length) ? args[i] : null;
      if (paramValue == null) {
        break;
      }
      paramArguments.add(paramValue);
    }
    i--;
    if (paramArguments.size() < param.required) {
      throw new CliException("Parameter required: " + param.label);
    }
    if (paramArguments.isEmpty()) {
      continue;
    }
    param.consumer.accept(paramArguments.toArray(new String[0]));
  }
  while (paramsIndex < params.size()) {
    final Param param=params.get(paramsIndex++);
    if (param.required > 0) {
      throw new CliException("Parameter required: " + param.label);
    }
  }
}

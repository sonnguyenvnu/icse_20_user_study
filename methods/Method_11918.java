private static void appendStackTraceLines(List<String> stackTraceLines,StringBuilder destBuilder){
  for (  String stackTraceLine : stackTraceLines) {
    destBuilder.append(String.format("%s%n",stackTraceLine));
  }
}

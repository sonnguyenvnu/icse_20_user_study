private static String format(List<String> specHierarchy){
  Collections.reverse(specHierarchy);
  final StringBuilder messageBuilder=new StringBuilder();
  messageBuilder.append(STACK_TRACE_SPEC_MESSAGE);
  int tabLevel=1;
  for (  String spec : specHierarchy) {
    for (int i=0; i < tabLevel; i++) {
      messageBuilder.append("\t");
    }
    tabLevel++;
    messageBuilder.append(spec);
    messageBuilder.append("\n");
  }
  return messageBuilder.toString();
}

@Override protected double analyze(List<String> socketStats){
  int index=-1;
  for (  final String str : socketStats) {
    if ((index=str.indexOf(key)) < 0) {
      continue;
    }
    int start=index + fixedSkipLen;
    int end=str.indexOf(" ",start);
    return getValue(str.substring(start,end));
  }
  return 0;
}

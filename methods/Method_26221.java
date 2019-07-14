private static int expectedArguments(String formatString){
  int count=0;
  for (int i=formatString.indexOf("%s"); i != -1; i=formatString.indexOf("%s",i + 1)) {
    count++;
  }
  return count;
}

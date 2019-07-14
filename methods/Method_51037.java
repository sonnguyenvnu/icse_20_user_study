private void analyzeLineOffsets(String sourceCode){
  String[] lines=sourceCode.split("\n");
  sourceCodeLength=sourceCode.length();
  int startOffset=0;
  int lineNumber=0;
  lineOffsets=new int[lines.length];
  for (  String line : lines) {
    lineOffsets[lineNumber]=startOffset;
    lineNumber++;
    startOffset+=line.length() + 1;
  }
}

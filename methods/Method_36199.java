private void writeLine(StringBuilder sb,String left,String right,String message){
  String[] leftLines=wrap(left).split(SEPARATOR);
  String[] rightLines=wrap(right).split(SEPARATOR);
  int maxLines=Math.max(leftLines.length,rightLines.length);
  writeSingleLine(sb,leftLines[0],rightLines[0],message);
  if (maxLines > 1) {
    for (int i=1; i < maxLines; i++) {
      String leftPart=leftLines.length > i ? leftLines[i] : "";
      String rightPart=rightLines.length > i ? rightLines[i] : "";
      writeSingleLine(sb,leftPart,rightPart,null);
    }
  }
}

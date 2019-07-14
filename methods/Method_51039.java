public int columnFromOffset(int lineNumber,int offset){
  int lineIndex=lineNumber - 1;
  if (lineIndex < 0 || lineIndex >= lineOffsets.length) {
    return 0;
  }
  int columnOffset=offset - lineOffsets[lineNumber - 1];
  return columnOffset + 1;
}

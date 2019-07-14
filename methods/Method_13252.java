@Override public void start(int maxLineNumber,int majorVersion,int minorVersion){
  super.start(maxLineNumber,majorVersion,minorVersion);
  if (showLineNumbers) {
    this.maxLineNumber=maxLineNumber;
    if (maxLineNumber > 0) {
      digitCount=1;
      unknownLineNumberPrefix=" ";
      int maximum=9;
      while (maximum < maxLineNumber) {
        digitCount++;
        unknownLineNumberPrefix+=' ';
        maximum=maximum * 10 + 9;
      }
      lineNumberBeginPrefix="/* ";
      lineNumberEndPrefix=" */ ";
    }
 else {
      unknownLineNumberPrefix="";
      lineNumberBeginPrefix="";
      lineNumberEndPrefix="";
    }
  }
 else {
    this.maxLineNumber=0;
    unknownLineNumberPrefix="";
    lineNumberBeginPrefix="";
    lineNumberEndPrefix="";
  }
}

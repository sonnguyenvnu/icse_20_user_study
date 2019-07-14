@Override public void start(int maxLineNumber,int majorVersion,int minorVersion){
  this.stringBuffer.setLength(0);
  this.majorVersion=majorVersion;
  this.minorVersion=minorVersion;
  this.indentationCount=0;
}

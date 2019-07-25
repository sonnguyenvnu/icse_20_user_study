private void reset(){
  result=null;
  buffer=null;
  lineSeparatorString=SysProperties.LINE_SEPARATOR;
  lineSeparator=lineSeparatorString.getBytes(charset);
}

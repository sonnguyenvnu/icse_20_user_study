private void err(String s){
  String msg=String.format("%s: %d: %s\n",fileName,line.n,s);
  msg+=line.s;
  throw new AsmException(msg);
}

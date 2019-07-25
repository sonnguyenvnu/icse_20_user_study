public void println(){
  String separator=System.lineSeparator();
  try {
    this.out.write(separator.toCharArray(),0,separator.length());
  }
 catch (  IOException ex) {
    throw new IllegalStateException(ex);
  }
  this.prependIndent=true;
}

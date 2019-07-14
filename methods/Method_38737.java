@Override public JsonContext createJsonContext(final Appendable appendable){
  return new JsonContext(this,appendable){
    @Override public void writeOpenArray(){
      deep++;
      super.writeOpenArray();
      if (breakOnNewObject) {
        breakLine(this);
      }
    }
    @Override public void writeCloseArray(){
      deep--;
      breakLine(this);
      super.writeCloseArray();
    }
    @Override public void writeOpenObject(){
      popName();
      deep++;
      write('{');
      if (breakOnNewObject) {
        breakLine(this);
      }
    }
    @Override public void writeCloseObject(){
      deep--;
      if (breakOnNewObject) {
        breakLine(this);
      }
      super.writeCloseObject();
    }
    @Override public void writeComma(){
      super.writeComma();
      breakLine(this);
    }
    @Override public void writeName(    final String name){
      if (name != null) {
        writeString(name);
      }
 else {
        write(NULL);
      }
      if (prefixSeparatorBySpace) {
        write(' ');
      }
      write(':');
      if (suffixSeparatorBySpace) {
        write(' ');
      }
    }
  }
;
}

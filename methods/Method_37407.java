protected void startSequence(final String value){
  if (prefix == null) {
    prefix=new StringBuilder();
    prefix.append("\u001B[");
  }
 else {
    prefix.append(StringPool.SEMICOLON);
  }
  prefix.append(value);
}

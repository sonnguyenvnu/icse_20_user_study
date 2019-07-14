public void endObject(){
  this.parser.accept(JSONToken.RBRACE);
  endStructure();
}

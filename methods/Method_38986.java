@Benchmark public LagartoParser lagarto_emitStrings(){
  LagartoParser lagartoParser=new LagartoParser(HTML1);
  lagartoParser.parse(new EmptyTagVisitor());
  return lagartoParser;
}

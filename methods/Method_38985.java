@Benchmark public LagartoParser lagarto_noStrings(){
  LagartoParser lagartoParser=new LagartoParser(HTML1);
  lagartoParser.parse(new EmptyTagVisitor());
  return lagartoParser;
}

@Benchmark public LagartoDOMBuilder lagartoDomBuilder(){
  LagartoDOMBuilder lagartoDOMBuilder=new LagartoDOMBuilder();
  lagartoDOMBuilder.parse(HTML1);
  return lagartoDOMBuilder;
}

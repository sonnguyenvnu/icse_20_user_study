@Override public Object unmarshal(final HierarchicalStreamReader reader,final UnmarshallingContext context){
  final String typeV=reader.getAttribute("type");
  final TooltipType type=parseType(typeV);
  final String wayV=reader.getAttribute("way");
  final TooltipWay way=parseWay(wayV);
  final String delayV=reader.getAttribute("delay");
  final Integer delay=parseDelay(delayV);
  final String text=reader.getValue();
  return new Tooltip(type,way,delay,text);
}

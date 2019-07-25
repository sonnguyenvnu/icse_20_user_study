@Override public Object unmarshal(final HierarchicalStreamReader reader,final UnmarshallingContext context){
  final Value value=new Value();
  value.setLang(reader.getAttribute(LANGUAGE));
  final String mnemonicValue=reader.getAttribute(MNEMONIC);
  value.setMnemonic(mnemonicValue != null ? mnemonicValue.charAt(0) : null);
  value.setHotkey(reader.getAttribute(HOTKEY));
  final String state=reader.getAttribute(STATE);
  final TooltipType tipType=TooltipConverter.parseType(reader.getAttribute(TIP_TYPE));
  final TooltipWay tipWay=TooltipConverter.parseWay(reader.getAttribute(TIP_WAY));
  final Integer tipDelay=TooltipConverter.parseDelay(reader.getAttribute(TIP_DELAY));
  final String text=reader.getValue();
  final List<Text> texts=new ArrayList<Text>();
  final List<Tooltip> tooltips=new ArrayList<Tooltip>();
  while (reader.hasMoreChildren()) {
    reader.moveDown();
    if (reader.getNodeName().equals("text")) {
      texts.add((Text)context.convertAnother(value,Text.class));
    }
 else     if (reader.getNodeName().equals("tooltip")) {
      tooltips.add((Tooltip)context.convertAnother(value,Tooltip.class));
    }
    reader.moveUp();
  }
  if (texts.size() == 0 && tooltips.size() == 0) {
    if (state != null && state.equals(TOOLTIP_KEY)) {
      value.setTooltips(CollectionUtils.copy(new Tooltip(tipType,tipWay,tipDelay,text)));
      value.setTexts(null);
    }
 else {
      value.setTexts(CollectionUtils.copy(new Text(text,state)));
      value.setTooltips(null);
    }
  }
 else {
    value.setTexts(texts.size() > 0 ? texts : null);
    value.setTooltips(tooltips.size() > 0 ? tooltips : null);
  }
  return value;
}

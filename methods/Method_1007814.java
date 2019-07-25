public Component create(int page) throws InvalidComponentException {
  if (page == 1 && getComponentsSize() == 0) {
    return getContents().reset().append("No results found.").create();
  }
  int pageCount=(int)Math.ceil(getComponentsSize() / (double)componentsPerPage);
  if (page < 1 || page > pageCount) {
    throw new InvalidComponentException("Invalid page number.");
  }
  currentPage=page;
  final int lastComp=Math.min(page * componentsPerPage,getComponentsSize());
  for (int i=(page - 1) * componentsPerPage; i < lastComp; i++) {
    getContents().append(getComponent(i));
    if (i + 1 != lastComp) {
      getContents().newline();
    }
  }
  if (pageCount == 1) {
    return super.create();
  }
  getContents().newline();
  TextComponent pageNumberComponent=TextComponent.of("Page ",TextColor.YELLOW).append(TextComponent.of(String.valueOf(page),TextColor.GOLD)).append(TextComponent.of(" of ")).append(TextComponent.of(String.valueOf(pageCount),TextColor.GOLD));
  if (pageCommand != null) {
    TextComponentProducer navProducer=new TextComponentProducer();
    if (page > 1) {
      TextComponent prevComponent=TextComponent.of("<<< ",TextColor.GOLD).clickEvent(ClickEvent.of(ClickEvent.Action.RUN_COMMAND,pageCommand.replace("%page%",String.valueOf(page - 1)))).hoverEvent(HoverEvent.of(HoverEvent.Action.SHOW_TEXT,TextComponent.of("Click to navigate")));
      navProducer.append(prevComponent);
    }
    navProducer.append(pageNumberComponent);
    if (page < pageCount) {
      TextComponent nextComponent=TextComponent.of(" >>>",TextColor.GOLD).clickEvent(ClickEvent.of(ClickEvent.Action.RUN_COMMAND,pageCommand.replace("%page%",String.valueOf(page + 1)))).hoverEvent(HoverEvent.of(HoverEvent.Action.SHOW_TEXT,TextComponent.of("Click to navigate")));
      navProducer.append(nextComponent);
    }
    getContents().append(centerAndBorder(navProducer.create()));
  }
 else {
    getContents().append(centerAndBorder(pageNumberComponent));
  }
  return super.create();
}

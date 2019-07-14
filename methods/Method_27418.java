private static HtmlSpanner initHtml(@NonNull TextView textView,int width){
  @PrefGetter.ThemeType int theme=PrefGetter.getThemeType();
  @ColorInt int windowBackground=getWindowBackground(theme);
  Drawable checked=ContextCompat.getDrawable(textView.getContext(),R.drawable.ic_checkbox_small);
  Drawable unchecked=ContextCompat.getDrawable(textView.getContext(),R.drawable.ic_checkbox_empty_small);
  HtmlSpanner mySpanner=new HtmlSpanner();
  mySpanner.setStripExtraWhiteSpace(true);
  mySpanner.registerHandler("pre",new PreTagHandler(windowBackground,true,theme));
  mySpanner.registerHandler("code",new PreTagHandler(windowBackground,false,theme));
  mySpanner.registerHandler("img",new DrawableHandler(textView,width));
  mySpanner.registerHandler("g-emoji",new EmojiHandler());
  mySpanner.registerHandler("blockquote",new QuoteHandler(windowBackground));
  mySpanner.registerHandler("b",new BoldHandler());
  mySpanner.registerHandler("strong",new BoldHandler());
  mySpanner.registerHandler("i",new ItalicHandler());
  mySpanner.registerHandler("em",new ItalicHandler());
  mySpanner.registerHandler("ul",new MarginHandler());
  mySpanner.registerHandler("ol",new MarginHandler());
  mySpanner.registerHandler("li",new ListsHandler(checked,unchecked));
  mySpanner.registerHandler("u",new UnderlineHandler());
  mySpanner.registerHandler("strike",new StrikethroughHandler());
  mySpanner.registerHandler("ins",new UnderlineHandler());
  mySpanner.registerHandler("del",new StrikethroughHandler());
  mySpanner.registerHandler("sub",new SubScriptHandler());
  mySpanner.registerHandler("sup",new SuperScriptHandler());
  mySpanner.registerHandler("a",new LinkHandler());
  mySpanner.registerHandler("hr",new HrHandler(windowBackground,width,false));
  mySpanner.registerHandler("emoji",new EmojiHandler());
  mySpanner.registerHandler("mention",new LinkHandler());
  mySpanner.registerHandler("h1",new HeaderHandler(1.5F));
  mySpanner.registerHandler("h2",new HeaderHandler(1.4F));
  mySpanner.registerHandler("h3",new HeaderHandler(1.3F));
  mySpanner.registerHandler("h4",new HeaderHandler(1.2F));
  mySpanner.registerHandler("h5",new HeaderHandler(1.1F));
  mySpanner.registerHandler("h6",new HeaderHandler(1.0F));
  if (width > 0) {
    TableHandler tableHandler=new TableHandler();
    tableHandler.setTextColor(ViewHelper.generateTextColor(windowBackground));
    tableHandler.setTableWidth(width);
    mySpanner.registerHandler("table",tableHandler);
  }
  return mySpanner;
}

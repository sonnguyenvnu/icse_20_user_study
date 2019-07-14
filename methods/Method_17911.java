static TextContent extractTextContent(List<?> items){
  final int size=items.size();
  if (size == 1) {
    Object item=items.get(0);
    return item instanceof TextContent ? (TextContent)item : TextContent.EMPTY;
  }
  final List<CharSequence> textContent=new ArrayList<>();
  for (int i=0; i < size; ++i) {
    final Object item=items.get(i);
    if (item instanceof TextContent) {
      textContent.addAll(((TextContent)item).getTextItems());
    }
  }
  return new TextContent(){
    @Override public List<CharSequence> getTextItems(){
      return textContent;
    }
  }
;
}

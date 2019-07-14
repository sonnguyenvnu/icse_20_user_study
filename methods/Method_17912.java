static ImageContent extractImageContent(List<?> items){
  final int size=items.size();
  if (size == 1) {
    Object item=items.get(0);
    return item instanceof ImageContent ? (ImageContent)item : ImageContent.EMPTY;
  }
  final List<Drawable> imageContent=new ArrayList<>();
  for (int i=0; i < size; ++i) {
    final Object item=items.get(i);
    if (item instanceof ImageContent) {
      imageContent.addAll(((ImageContent)item).getImageItems());
    }
  }
  return new ImageContent(){
    @Override public List<Drawable> getImageItems(){
      return imageContent;
    }
  }
;
}

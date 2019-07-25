public FontConfiguration mute(Colors colors){
  if (colors == null) {
    throw new IllegalArgumentException();
  }
  final HtmlColor color=colors.getColor(ColorType.TEXT);
  if (color == null) {
    return this;
  }
  return changeColor(color);
}

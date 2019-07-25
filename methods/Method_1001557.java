private TextBlock decorate(StringBounder stringBounder,TextBlock b,char separator,TextBlock title){
  if (separator == 0) {
    return b;
  }
  if (title == null) {
    return new TextBlockLineBefore(TextBlockUtils.withMargin(b,6,4),separator);
  }
  final Dimension2D dimTitle=title.calculateDimension(stringBounder);
  final TextBlock raw=new TextBlockLineBefore(TextBlockUtils.withMargin(b,6,6,dimTitle.getHeight() / 2,4),separator,title);
  return TextBlockUtils.withMargin(raw,0,0,dimTitle.getHeight() / 2,0);
}

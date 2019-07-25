public static TextBlock bordered(TextBlock textBlock,UStroke stroke,HtmlColor borderColor,HtmlColor backgroundColor,double cornersize){
  return new TextBlockBordered(textBlock,stroke,borderColor,backgroundColor,cornersize);
}

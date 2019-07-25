public void paint(UGraphic ug){
  int n=lines.size();
  for (int i=0; i < n; i++) {
    final HtmlColor color=colors.get(i);
    final Rectangle2D.Double r=lines.get(i);
    final ULine line=new ULine(r.width - r.x,-r.height + r.y);
    ug.apply(new UChangeColor(color)).apply(new UTranslate(r.x,-r.y)).draw(line);
  }
  drawTurtle(ug);
  if (message != null) {
    final FontConfiguration font=FontConfiguration.blackBlueTrue(new UFont("",Font.PLAIN,14));
    final TextBlock text=Display.create(message).create(font,HorizontalAlignment.LEFT,new SpriteContainerEmpty());
    final Dimension2D dim=text.calculateDimension(ug.getStringBounder());
    final double textHeight=dim.getHeight();
    text.drawU(ug.apply(new UTranslate(0,(height - textHeight))));
  }
}

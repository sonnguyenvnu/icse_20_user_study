private ScrollPane allGlyphs(){
  List<SVGGlyph> glyphs=SVGGlyphLoader.getAllGlyphsIDs().stream().map(glyphName -> {
    try {
      return SVGGlyphLoader.getIcoMoonGlyph(glyphName);
    }
 catch (    Exception e) {
      return null;
    }
  }
).collect(Collectors.toList());
  glyphs.sort(Comparator.comparing(SVGGlyph::getName));
  glyphs.forEach(glyph -> glyph.setSize(16));
  List<Button> iconButtons=glyphs.stream().map(this::createIconButton).collect(Collectors.toList());
  iconButtons.forEach(button -> button.setCache(true));
  Platform.runLater(() -> iconButtons.get(0).fire());
  FlowPane glyphLayout=new FlowPane();
  glyphLayout.setHgap(10);
  glyphLayout.setVgap(10);
  glyphLayout.setPadding(new Insets(10));
  glyphLayout.getChildren().setAll(iconButtons);
  glyphLayout.setPrefSize(600,300);
  ScrollPane scrollableGlyphs=new ScrollPane(glyphLayout);
  scrollableGlyphs.setFitToWidth(true);
  return scrollableGlyphs;
}

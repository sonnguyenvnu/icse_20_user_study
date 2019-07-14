private static void getApplicableStyles(List<WebvttCssStyle> declaredStyles,String id,StartTag tag,List<StyleMatch> output){
  int styleCount=declaredStyles.size();
  for (int i=0; i < styleCount; i++) {
    WebvttCssStyle style=declaredStyles.get(i);
    int score=style.getSpecificityScore(id,tag.name,tag.classes,tag.voice);
    if (score > 0) {
      output.add(new StyleMatch(score,style));
    }
  }
  Collections.sort(output);
}

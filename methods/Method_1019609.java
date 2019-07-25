/** 
 * Applies this theme to a text area.
 * @param textArea The text area to apply this theme to.
 */
public void apply(RSyntaxTextArea textArea){
  textArea.setFont(baseFont);
  textArea.setBackground(bgColor);
  textArea.setCaretColor(caretColor);
  textArea.setUseSelectedTextColor(useSelctionFG);
  textArea.setSelectedTextColor(selectionFG);
  textArea.setSelectionColor(selectionBG);
  textArea.setRoundedSelectionEdges(selectionRoundedEdges);
  textArea.setCurrentLineHighlightColor(currentLineHighlight);
  textArea.setFadeCurrentLineHighlight(fadeCurrentLineHighlight);
  textArea.setMarginLineColor(marginLineColor);
  textArea.setMarkAllHighlightColor(markAllHighlightColor);
  textArea.setMarkOccurrencesColor(markOccurrencesColor);
  textArea.setPaintMarkOccurrencesBorder(markOccurrencesBorder);
  textArea.setMatchedBracketBGColor(matchedBracketBG);
  textArea.setMatchedBracketBorderColor(matchedBracketFG);
  textArea.setPaintMatchedBracketPair(matchedBracketHighlightBoth);
  textArea.setAnimateBracketMatching(matchedBracketAnimate);
  textArea.setHyperlinkForeground(hyperlinkFG);
  int count=secondaryLanguages.length;
  for (int i=0; i < count; i++) {
    textArea.setSecondaryLanguageBackground(i + 1,secondaryLanguages[i]);
  }
  textArea.setSyntaxScheme(scheme);
  Gutter gutter=RSyntaxUtilities.getGutter(textArea);
  if (gutter != null) {
    gutter.setBackground(gutterBackgroundColor);
    gutter.setBorderColor(gutterBorderColor);
    gutter.setActiveLineRangeColor(activeLineRangeColor);
    gutter.setIconRowHeaderInheritsGutterBackground(iconRowHeaderInheritsGutterBG);
    gutter.setLineNumberColor(lineNumberColor);
    String fontName=lineNumberFont != null ? lineNumberFont : baseFont.getFamily();
    int fontSize=lineNumberFontSize > 0 ? lineNumberFontSize : baseFont.getSize();
    Font font=getFont(fontName,Font.PLAIN,fontSize);
    gutter.setLineNumberFont(font);
    gutter.setFoldIndicatorForeground(foldIndicatorFG);
    gutter.setFoldBackground(foldBG);
    gutter.setArmedFoldBackground(armedFoldBG);
  }
}

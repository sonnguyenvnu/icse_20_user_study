/** 
 * Saves this theme to an output stream.
 * @param out The output stream to write to.
 * @throws IOException If an IO error occurs.
 * @see #load(InputStream)
 */
public void save(OutputStream out) throws IOException {
  try (BufferedOutputStream bout=new BufferedOutputStream(out)){
    DocumentBuilder db=DocumentBuilderFactory.newInstance().newDocumentBuilder();
    DOMImplementation impl=db.getDOMImplementation();
    Document doc=impl.createDocument(null,"RSyntaxTheme",null);
    Element root=doc.getDocumentElement();
    root.setAttribute("version","1.0");
    Element elem=doc.createElement("baseFont");
    if (!baseFont.getFamily().equals(RSyntaxTextArea.getDefaultFont().getFamily())) {
      elem.setAttribute("family",baseFont.getFamily());
    }
    elem.setAttribute("size",Integer.toString(baseFont.getSize()));
    root.appendChild(elem);
    elem=doc.createElement("background");
    elem.setAttribute("color",colorToString(bgColor));
    root.appendChild(elem);
    elem=doc.createElement("caret");
    elem.setAttribute("color",colorToString(caretColor));
    root.appendChild(elem);
    elem=doc.createElement("selection");
    elem.setAttribute("useFG",Boolean.toString(useSelctionFG));
    elem.setAttribute("fg",colorToString(selectionFG));
    elem.setAttribute("bg",colorToString(selectionBG));
    elem.setAttribute("roundedEdges",Boolean.toString(selectionRoundedEdges));
    root.appendChild(elem);
    elem=doc.createElement("currentLineHighlight");
    elem.setAttribute("color",colorToString(currentLineHighlight));
    elem.setAttribute("fade",Boolean.toString(fadeCurrentLineHighlight));
    root.appendChild(elem);
    elem=doc.createElement("marginLine");
    elem.setAttribute("fg",colorToString(marginLineColor));
    root.appendChild(elem);
    elem=doc.createElement("markAllHighlight");
    elem.setAttribute("color",colorToString(markAllHighlightColor));
    root.appendChild(elem);
    elem=doc.createElement("markOccurrencesHighlight");
    elem.setAttribute("color",colorToString(markOccurrencesColor));
    elem.setAttribute("border",Boolean.toString(markOccurrencesBorder));
    root.appendChild(elem);
    elem=doc.createElement("matchedBracket");
    elem.setAttribute("fg",colorToString(matchedBracketFG));
    elem.setAttribute("bg",colorToString(matchedBracketBG));
    elem.setAttribute("highlightBoth",Boolean.toString(matchedBracketHighlightBoth));
    elem.setAttribute("animate",Boolean.toString(matchedBracketAnimate));
    root.appendChild(elem);
    elem=doc.createElement("hyperlinks");
    elem.setAttribute("fg",colorToString(hyperlinkFG));
    root.appendChild(elem);
    elem=doc.createElement("secondaryLanguages");
    for (int i=0; i < secondaryLanguages.length; i++) {
      Color color=secondaryLanguages[i];
      Element elem2=doc.createElement("language");
      elem2.setAttribute("index",Integer.toString(i + 1));
      elem2.setAttribute("bg",color == null ? "" : colorToString(color));
      elem.appendChild(elem2);
    }
    root.appendChild(elem);
    elem=doc.createElement("gutterBackground");
    elem.setAttribute("color",colorToString(gutterBackgroundColor));
    root.appendChild(elem);
    elem=doc.createElement("gutterBorder");
    elem.setAttribute("color",colorToString(gutterBorderColor));
    root.appendChild(elem);
    elem=doc.createElement("lineNumbers");
    elem.setAttribute("fg",colorToString(lineNumberColor));
    if (lineNumberFont != null) {
      elem.setAttribute("fontFamily",lineNumberFont);
    }
    if (lineNumberFontSize > 0) {
      elem.setAttribute("fontSize",Integer.toString(lineNumberFontSize));
    }
    root.appendChild(elem);
    elem=doc.createElement("foldIndicator");
    elem.setAttribute("fg",colorToString(foldIndicatorFG));
    elem.setAttribute("iconBg",colorToString(foldBG));
    elem.setAttribute("iconArmedBg",colorToString(armedFoldBG));
    root.appendChild(elem);
    elem=doc.createElement("iconRowHeader");
    elem.setAttribute("activeLineRange",colorToString(activeLineRangeColor));
    elem.setAttribute("inheritsGutterBG",Boolean.toString(iconRowHeaderInheritsGutterBG));
    root.appendChild(elem);
    elem=doc.createElement("tokenStyles");
    Field[] fields=TokenTypes.class.getFields();
    for (    Field field : fields) {
      int value=field.getInt(null);
      if (value != TokenTypes.DEFAULT_NUM_TOKEN_TYPES) {
        Style style=scheme.getStyle(value);
        if (style != null) {
          Element elem2=doc.createElement("style");
          elem2.setAttribute("token",field.getName());
          Color fg=style.foreground;
          if (fg != null) {
            elem2.setAttribute("fg",colorToString(fg));
          }
          Color bg=style.background;
          if (bg != null) {
            elem2.setAttribute("bg",colorToString(bg));
          }
          Font font=style.font;
          if (font != null) {
            if (!font.getFamily().equals(baseFont.getFamily())) {
              elem2.setAttribute("fontFamily",font.getFamily());
            }
            if (font.getSize() != baseFont.getSize()) {
              elem2.setAttribute("fontSize",Integer.toString(font.getSize()));
            }
            if (font.isBold()) {
              elem2.setAttribute("bold","true");
            }
            if (font.isItalic()) {
              elem2.setAttribute("italic","true");
            }
          }
          if (style.underline) {
            elem2.setAttribute("underline","true");
          }
          elem.appendChild(elem2);
        }
      }
    }
    root.appendChild(elem);
    DOMSource source=new DOMSource(doc);
    StreamResult result=new StreamResult(new PrintWriter(new UnicodeWriter(bout,"UTF-8")));
    TransformerFactory transFac=TransformerFactory.newInstance();
    Transformer transformer=transFac.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT,"yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
    transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
    transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"theme.dtd");
    transformer.transform(source,result);
  }
 catch (  RuntimeException re) {
    throw re;
  }
catch (  Exception e) {
    throw new IOException("Error generating XML: " + e.getMessage(),e);
  }
}

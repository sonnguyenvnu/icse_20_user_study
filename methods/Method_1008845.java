public static void process(WordprocessingMLPackage wmlPackage){
  Throwable t=new Throwable();
  StackTraceElement[] trace=t.getStackTrace();
  for (int i=0; i < trace.length; i++) {
    if (trace[i].getClassName().contains("FOPAreaTreeHelper")) {
      return;
    }
  }
  StyleRenamer styleRenamer=new StyleRenamer();
  try {
    DocumentSettingsPart dsp=wmlPackage.getMainDocumentPart().getDocumentSettingsPart();
    if (dsp == null) {
      dsp=new DocumentSettingsPart();
      wmlPackage.getMainDocumentPart().addTargetPart(dsp);
      dsp.setContents(Context.getWmlObjectFactory().createCTSettings());
    }
 else {
      styleRenamer.overrideTableStyleFontSizeAndJustification=dsp.getWordCompatSetting("overrideTableStyleFontSizeAndJustification");
      if (styleRenamer.overrideTableStyleFontSizeAndJustification == null) {
        styleRenamer.overrideTableStyleFontSizeAndJustification=defaultSetting;
      }
    }
    dsp.setWordCompatSetting("overrideTableStyleFontSizeAndJustification","1");
  }
 catch (  Docx4JException e) {
    log.error(e.getMessage(),e);
  }
  try {
    styleRenamer.setDefaultParagraphStyle(wmlPackage.getMainDocumentPart().getStyleDefinitionsPart().getDefaultParagraphStyle().getStyleId());
  }
 catch (  NullPointerException npe) {
    log.warn("No default paragraph style!!");
  }
  Style defaultTableStyle=wmlPackage.getMainDocumentPart().getStyleDefinitionsPart().getDefaultTableStyle();
  if (defaultTableStyle != null) {
    styleRenamer.setDefaultTableStyle(defaultTableStyle);
  }
  Styles styles=wmlPackage.getMainDocumentPart().getStyleDefinitionsPart().getJaxbElement();
  styleRenamer.propertyResolver=wmlPackage.getMainDocumentPart().getPropertyResolver();
  styleRenamer.setStyles(styles);
  try {
    new TraversalUtil(wmlPackage.getMainDocumentPart().getContents(),styleRenamer);
  }
 catch (  Docx4JException e) {
    log.error(e.getMessage(),e);
  }
}

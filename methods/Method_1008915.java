private void init() throws Docx4JException {
  try {
    defaultParagraphStyleId=this.styleDefinitionsPart.getDefaultParagraphStyle().getStyleId();
  }
 catch (  NullPointerException npe) {
    log.warn("No default paragraph style!!");
  }
  try {
    defaultCharacterStyleId=this.styleDefinitionsPart.getDefaultCharacterStyle().getStyleId();
  }
 catch (  NullPointerException npe) {
    log.warn("No default character style!!");
  }
  styles=(org.docx4j.wml.Styles)styleDefinitionsPart.getJaxbElement();
  initialiseLiveStyles();
  DocDefaults docDefaults=styleDefinitionsPart.getJaxbElement().getDocDefaults();
  if (log.isDebugEnabled()) {
    log.debug(XmlUtils.marshaltoString(docDefaults,true,true));
  }
  documentDefaultPPr=new PPr();
  documentDefaultRPr=new RPr();
  if (docDefaults != null && docDefaults.getPPrDefault() != null && docDefaults.getPPrDefault().getPPr() != null) {
    documentDefaultPPr=docDefaults.getPPrDefault().getPPr();
  }
  if (docDefaults != null && docDefaults.getRPrDefault() != null && docDefaults.getRPrDefault().getRPr() != null) {
    documentDefaultRPr=docDefaults.getRPrDefault().getRPr();
  }
  if (documentDefaultRPr.getSz() == null) {
    HpsMeasure sz20=new HpsMeasure();
    sz20.setVal(BigInteger.valueOf(20));
    documentDefaultRPr.setSz(sz20);
  }
  addNormalToResolvedStylePPrComponent();
  addDefaultParagraphFontToResolvedStyleRPrComponent();
}

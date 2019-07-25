public static void process(WordprocessingMLPackage wmlPackage){
  Body body=wmlPackage.getMainDocumentPart().getJaxbElement().getBody();
  if (body == null || body.getContent().size() == 0) {
    log.warn("w:document/w:body null or empty");
    return;
  }
  Object o=body.getContent().get(0);
  if (o instanceof P && ((P)o).getPPr() != null) {
    PPr pPr=((P)o).getPPr();
    BooleanDefaultTrue val=new BooleanDefaultTrue();
    val.setVal(Boolean.FALSE);
    pPr.setPageBreakBefore(val);
  }
}

private void compute(CTDocument1 document) throws Exception {
  XmlCursor cursor=document.getBody().newCursor();
  cursor.selectPath("./*");
  while (cursor.toNextSelection()) {
    XmlObject o=cursor.getObject();
    if (o instanceof CTP) {
      CTP paragraph=(CTP)o;
      CTSectPr sectPr=getSectPr(paragraph);
      if (sectPr != null) {
        addSection(sectPr,true);
      }
    }
  }
  addSection(bodySectPr,false);
}

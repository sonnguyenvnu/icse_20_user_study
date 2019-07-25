@Override public List<Object> apply(Object o){
  if (currentSDT != null) {
    if (o instanceof P && isDocPartToC(currentSDT) && !foundToC) {
      tocHeadingP.add((P)o);
    }
 else     if (o instanceof JAXBElement && ((JAXBElement)o).getName().getLocalPart().equals("instrText")) {
      Text instr=(Text)XmlUtils.unwrap(o);
      if (instr.getValue().contains("TOC")) {
        tocInstruction=instr.getValue();
        tocSDT=currentSDT;
        foundToC=true;
        log.debug("found the sdt!");
        removeLastHeadingP();
      }
    }
 else     if (o instanceof JAXBElement && ((JAXBElement)o).getName().getLocalPart().equals("fldSimple")) {
      CTSimpleField fldSimple=(CTSimpleField)XmlUtils.unwrap(o);
      if (fldSimple.getInstr().contains("TOC")) {
        tocInstruction=fldSimple.getInstr();
        tocSDT=currentSDT;
        foundToC=true;
        log.debug("found the sdt!");
        removeLastHeadingP();
      }
    }
 else     if (o instanceof CTSimpleField) {
      CTSimpleField fldSimple=(CTSimpleField)o;
      if (fldSimple.getInstr().contains("TOC")) {
        tocInstruction=fldSimple.getInstr();
        tocSDT=currentSDT;
        foundToC=true;
        log.debug("found the sdt!");
        removeLastHeadingP();
      }
    }
  }
  if (foundToC) {
    if (o instanceof P) {
      P p=(P)o;
      if (p.getPPr() != null && p.getPPr().getSectPr() != null) {
        sectPr=p.getPPr().getSectPr();
      }
    }
  }
  return null;
}

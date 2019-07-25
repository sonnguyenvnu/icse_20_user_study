public WordprocessingMLPackage migrate(WordprocessingMLPackage pkgIn) throws Exception {
  WordprocessingMLPackage pkgOut=(WordprocessingMLPackage)pkgIn.clone();
  createParts(pkgOut);
  FieldsPreprocessor.complexifyFields(pkgOut.getMainDocumentPart());
  if (log.isDebugEnabled()) {
    log.debug("complexified: " + XmlUtils.marshaltoString(pkgOut.getMainDocumentPart().getJaxbElement(),true));
  }
  ComplexFieldLocator fl=new ComplexFieldLocator();
  new TraversalUtil(pkgOut.getMainDocumentPart().getContent(),fl);
  log.info("Found " + fl.getStarts().size() + " fields ");
  List<FieldRef> fieldRefs=new ArrayList<FieldRef>();
  for (  P p : fl.getStarts()) {
    int index=((ContentAccessor)p.getParent()).getContent().indexOf(p);
    P newP=FieldsPreprocessor.canonicalise(p,fieldRefs);
    ((ContentAccessor)p.getParent()).getContent().set(index,newP);
  }
  for (  FieldRef fr : fieldRefs) {
    if (fr.getFldName().equals("MERGEFIELD")) {
      String instr=extractInstr(fr.getInstructions());
      String tmp=instr.substring(instr.indexOf("MERGEFIELD") + 10);
      tmp=tmp.trim();
      String key=tmp.indexOf(" ") > -1 ? tmp.substring(0,tmp.indexOf(" ")) : tmp;
      log.info("Key: '" + key + "'");
      int end=fr.getParent().getContent().indexOf(fr.getEndRun());
      int begin=fr.getParent().getContent().indexOf(fr.getBeginRun());
      for (int i=end; i >= begin; i--) {
        fr.getParent().getContent().remove(i);
      }
      List<Object> replacementContent=new ArrayList<Object>();
      createContentControl(null,replacementContent,key);
      fr.getParent().getContent().addAll(begin,replacementContent);
    }
  }
  return pkgOut;
}

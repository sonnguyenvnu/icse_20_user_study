public void build(CTSimpleField fldSimple,Node content) throws TransformerException {
  this.fldSimple=fldSimple;
  if (log.isDebugEnabled()) {
    log.debug("\n" + XmlUtils.marshaltoString(fldSimple,true,true));
  }
  this.content=content;
  setupNameParameterString(fldSimple.getInstr());
}

private long restart(Num num,long ilvl,long val) throws InvalidOperationException {
  if (num == null) {
    throw new InvalidOperationException("Abstract List does not exist!");
  }
  if (em == null) {
    getEmulator();
  }
  BigInteger abstractNumIdVal=num.getAbstractNumId().getVal();
  long newNumId=instanceListDefinitions.size() + 1;
  org.docx4j.wml.ObjectFactory factory=Context.getWmlObjectFactory();
  Num newNum=factory.createNumberingNum();
  newNum.setNumId(BigInteger.valueOf(newNumId));
  AbstractNumId abstractNumId=factory.createNumberingNumAbstractNumId();
  abstractNumId.setVal(abstractNumIdVal);
  newNum.setAbstractNumId(abstractNumId);
  LvlOverride lvlOverride=factory.createNumberingNumLvlOverride();
  lvlOverride.setIlvl(BigInteger.valueOf(ilvl));
  newNum.getLvlOverride().add(lvlOverride);
  StartOverride start=factory.createNumberingNumLvlOverrideStartOverride();
  start.setVal(BigInteger.valueOf(val));
  lvlOverride.setStartOverride(start);
  ((Numbering)getJaxbElement()).getNum().add(newNum);
  ListNumberingDefinition listDef=new ListNumberingDefinition(newNum,abstractListDefinitions);
  instanceListDefinitions.put(listDef.getListNumberId(),listDef);
  return newNumId;
}

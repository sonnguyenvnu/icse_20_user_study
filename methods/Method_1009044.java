public Condition repeat(String xpathBase,int index,Map<String,Condition> conditionsMap,Map<String,org.opendope.xpaths.Xpaths.Xpath> xpathsMap){
  Condition newCondition;
  if (this.getParticle() instanceof Xpathref) {
    newCondition=new Condition();
    Xpathref xpathref=new Xpathref();
    xpathref.setId(((Xpathref)this.getParticle()).getId());
    newCondition.setParticle(xpathref);
  }
 else   if ((this.getParticle() instanceof Not) && (((Not)this.getParticle()).getParticle() instanceof Xpathref)) {
    newCondition=new Condition();
    Not notParticle=new Not();
    newCondition.setParticle(notParticle);
    Xpathref xpathref=new Xpathref();
    xpathref.setId(((Xpathref)((Not)this.getParticle()).getParticle()).getId());
    notParticle.setParticle(xpathref);
  }
 else {
    newCondition=XmlUtils.deepCopy(this);
  }
  String newConditionId=id + "_" + index;
  newCondition.setId(newConditionId);
  Condition preExistingSanity=conditionsMap.put(newCondition.getId(),newCondition);
  if (preExistingSanity != null) {
    String preExisting=XmlUtils.marshaltoString(preExistingSanity);
    String newC=XmlUtils.marshaltoString(newCondition);
    if (preExisting.equals(newC)) {
      log.debug("Duplicate identical Condition being added: " + newCondition.getId());
    }
 else {
      log.error("Duplicate Condition " + newCondition.getId() + ": " + "\n" + newC + " overwriting " + "\n" + preExisting);
    }
  }
  newCondition.getParticle().repeat(xpathBase,index,conditionsMap,xpathsMap);
  return newCondition;
}

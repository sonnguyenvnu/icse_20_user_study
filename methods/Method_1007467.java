public ArrayList<MethodFlow> analyze(boolean forceAnalysis) throws KilimException {
  try {
    cr.accept(this,ClassReader.SKIP_FRAMES);
    for (    Object o : this.fields) {
      FieldNode fn=(FieldNode)o;
      if (fn.name.equals(Constants.WOVEN_FIELD)) {
        isWoven=true;
        break;
      }
    }
    if (isWoven && !forceAnalysis)     return new ArrayList<MethodFlow>();
    cr=null;
    classDesc=TypeDesc.getInterned("L" + name + ';');
    ArrayList<MethodFlow> flows=new ArrayList<MethodFlow>(methods.size());
    String msg="";
    for (    Object o : methods) {
      try {
        MethodFlow mf=(MethodFlow)o;
        if (mf.isBridge()) {
          MethodFlow mmf=getOrigWithSameSig(mf);
          if (mmf != null)           mf.setPausable(mmf.isPausable());
        }
        mf.verifyPausables();
        if (mf.isPausable())         isPausable=true;
        if ((mf.needsWeaving() || forceAnalysis) && (!mf.isAbstract())) {
          mf.analyze();
        }
        flows.add(mf);
      }
 catch (      KilimException ke) {
        msg=msg + ke.getMessage() + "\n-------------------------------------------------\n";
      }
    }
    if (msg.length() > 0) {
      throw new KilimException(msg);
    }
    methodFlows=flows;
    return flows;
  }
  finally {
  }
}

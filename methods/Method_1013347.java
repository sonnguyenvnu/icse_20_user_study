public LiveExprNode simplify(){
  LNConj lnc1=new LNConj(getCount());
  for (int i=0; i < getCount(); i++) {
    LiveExprNode elem=getBody(i).simplify();
    if (elem instanceof LNBool) {
      if (!((LNBool)elem).b) {
        return LNBool.FALSE;
      }
    }
 else {
      lnc1.addConj(elem);
    }
  }
  if (lnc1.getCount() == 0) {
    return LNBool.TRUE;
  }
  if (lnc1.getCount() == 1) {
    return lnc1.getBody(0);
  }
  return lnc1;
}

public LiveExprNode simplify(){
  LNDisj lnd1=new LNDisj(getCount());
  for (int i=0; i < getCount(); i++) {
    LiveExprNode elem=getBody(i).simplify();
    if (elem instanceof LNBool) {
      if (((LNBool)elem).b) {
        return LNBool.TRUE;
      }
    }
 else {
      lnd1.addDisj(elem);
    }
  }
  if (lnd1.getCount() == 0) {
    return LNBool.FALSE;
  }
  if (lnd1.getCount() == 1) {
    return lnd1.getBody(0);
  }
  return lnd1;
}

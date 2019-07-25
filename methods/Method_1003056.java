@Override public void init(){
  if (checkInit) {
    DbException.throwInternalError();
  }
  checkInit=true;
  if (withTies && !hasOrder()) {
    throw DbException.get(ErrorCode.WITH_TIES_WITHOUT_ORDER_BY);
  }
}

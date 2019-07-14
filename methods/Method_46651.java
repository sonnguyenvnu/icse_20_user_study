@Override public void join(DTXContext dtxContext,String unitId,String unitType,String modId,int userState) throws TransactionException {
  if (userState == 0) {
    dtxContext.resetTransactionState(0);
  }
  TransactionUnit transactionUnit=new TransactionUnit();
  transactionUnit.setModId(modId);
  transactionUnit.setUnitId(unitId);
  transactionUnit.setUnitType(unitType);
  dtxContext.join(transactionUnit);
}

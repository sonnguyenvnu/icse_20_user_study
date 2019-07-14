static TxLogger newLogger(Class<?> type){
  return new DefaultTxLogger(type);
}

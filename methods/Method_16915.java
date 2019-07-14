@Override protected void execute(){
  addVariableExpiration();
  addAccessExpiration();
  addWriteExpiration();
  addRefreshExpiration();
}

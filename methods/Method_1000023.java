private void precompiled() throws ContractValidateException, ContractExeException {
  TransactionCapsule trxCap=new TransactionCapsule(trx);
  final List<Actuator> actuatorList=ActuatorFactory.createActuator(trxCap,deposit.getDbManager());
  for (  Actuator act : actuatorList) {
    act.validate();
    act.execute(result.getRet());
  }
}

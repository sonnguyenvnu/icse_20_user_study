public void execute() throws ContractValidateException, ContractExeException, VMIllegalException {
switch (trxType) {
case TRX_PRECOMPILED_TYPE:
    precompiled();
  break;
case TRX_CONTRACT_CREATION_TYPE:
create();
break;
case TRX_CONTRACT_CALL_TYPE:
call();
break;
default :
throw new ContractValidateException("Unknown contract type");
}
}

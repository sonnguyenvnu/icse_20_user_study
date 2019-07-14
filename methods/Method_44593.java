private static Status convert(org.knowm.xchange.luno.dto.account.LunoWithdrawals.Status status){
switch (status) {
case PENDING:
    return Status.PROCESSING;
case COMPLETED:
  return Status.COMPLETE;
case CANCELLED:
return Status.CANCELLED;
case UNKNOWN:
default :
throw new ExchangeException("Unknown status for luno withdrawal: " + status);
}
}

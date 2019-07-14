/** 
 * ??????
 * @param info info
 * @return  Object Object
 * @throws Throwable Throwable
 */
default Object doBusinessCode(TxTransactionInfo info) throws Throwable {
  return info.getBusinessCallback().call();
}

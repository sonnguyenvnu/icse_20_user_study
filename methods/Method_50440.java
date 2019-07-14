/** 
 * Executor object.
 * @param transId         the trans id
 * @param actionEnum      the action enum
 * @param hmilyInvocation the hmily invocation
 * @return the object
 * @throws Exception the exception
 */
public static Object executor(final String transId,final HmilyActionEnum actionEnum,final HmilyInvocation hmilyInvocation) throws Exception {
  HmilyTransactionContext context=new HmilyTransactionContext();
  context.setAction(actionEnum.getCode());
  context.setTransId(transId);
  context.setRole(HmilyRoleEnum.START.getCode());
  HmilyTransactionContextLocal.getInstance().set(context);
  return execute(hmilyInvocation);
}

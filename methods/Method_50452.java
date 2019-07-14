/** 
 * hmily transaction aspect.
 * @param hmilyTransactionContext {@linkplain HmilyTransactionContext}
 * @param point                   {@linkplain ProceedingJoinPoint}
 * @return object  return value
 * @throws Throwable exception
 */
@Override public Object invoke(final HmilyTransactionContext hmilyTransactionContext,final ProceedingJoinPoint point) throws Throwable {
  final Class clazz=hmilyTransactionFactoryService.factoryOf(hmilyTransactionContext);
  final HmilyTransactionHandler txTransactionHandler=(HmilyTransactionHandler)SpringBeanUtils.getInstance().getBean(clazz);
  return txTransactionHandler.handler(point,hmilyTransactionContext);
}

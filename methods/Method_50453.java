/** 
 * acquired HmilyTransactionHandler.
 * @param context {@linkplain HmilyTransactionContext}
 * @return Class
 */
@Override public Class factoryOf(final HmilyTransactionContext context){
  if (Objects.isNull(context)) {
    return StarterHmilyTransactionHandler.class;
  }
 else {
    if (context.getRole() == HmilyRoleEnum.SPRING_CLOUD.getCode()) {
      context.setRole(HmilyRoleEnum.START.getCode());
      return ConsumeHmilyTransactionHandler.class;
    }
    if (context.getRole() == HmilyRoleEnum.LOCAL.getCode()) {
      return LocalHmilyTransactionHandler.class;
    }
 else     if (context.getRole() == HmilyRoleEnum.START.getCode() || context.getRole() == HmilyRoleEnum.INLINE.getCode()) {
      return ParticipantHmilyTransactionHandler.class;
    }
    return ConsumeHmilyTransactionHandler.class;
  }
}

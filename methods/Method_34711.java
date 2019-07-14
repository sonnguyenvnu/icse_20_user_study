/** 
 * Add  {@link HystrixCommand} instance to the request log.
 * @param command {@code HystrixCommand<?>}
 */
void addExecutedCommand(HystrixInvokableInfo<?> command){
  if (!allExecutedCommands.offer(command)) {
    logger.warn("RequestLog ignoring command after reaching limit of " + MAX_STORAGE + ". See https://github.com/Netflix/Hystrix/issues/53 for more information.");
  }
  if (command instanceof HystrixCommand) {
    @SuppressWarnings("rawtypes") HystrixCommand<?> _c=(HystrixCommand)command;
    if (!executedCommands.offer(_c)) {
      logger.warn("RequestLog ignoring command after reaching limit of " + MAX_STORAGE + ". See https://github.com/Netflix/Hystrix/issues/53 for more information.");
    }
  }
}

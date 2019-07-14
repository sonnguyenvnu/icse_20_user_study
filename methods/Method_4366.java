@EnsuresNonNullIf(result=true,expression="sessionId") @SuppressWarnings("contracts.conditional.postcondition.not.satisfied") private boolean isOpen(){
  return state == STATE_OPENED || state == STATE_OPENED_WITH_KEYS;
}

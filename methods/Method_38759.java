/** 
 * Requests transaction with specified  {@link JtxTransactionMode mode}. Depending on propagation behavior, it will return either <b>existing</b> or <b>new</b> transaction. Only one transaction can be opened over one scope. The exception may be thrown indicating propagation mismatch.
 */
public JtxTransaction requestTransaction(final JtxTransactionMode mode,final Object scope){
  if (log.isDebugEnabled()) {
    log.debug("Requesting TX " + mode.toString());
  }
  JtxTransaction currentTx=getTransaction();
  if (!isNewTxScope(currentTx,scope)) {
    return currentTx;
  }
switch (mode.getPropagationBehavior()) {
case PROPAGATION_REQUIRED:
    return propRequired(currentTx,mode,scope);
case PROPAGATION_SUPPORTS:
  return propSupports(currentTx,mode,scope);
case PROPAGATION_MANDATORY:
return propMandatory(currentTx,mode,scope);
case PROPAGATION_REQUIRES_NEW:
return propRequiresNew(currentTx,mode,scope);
case PROPAGATION_NOT_SUPPORTED:
return propNotSupported(currentTx,mode,scope);
case PROPAGATION_NEVER:
return propNever(currentTx,mode,scope);
}
throw new JtxException("Invalid TX propagation value: " + mode.getPropagationBehavior().value());
}

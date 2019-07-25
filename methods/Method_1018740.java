/** 
 * Interrupts execution of this command, typically from another thread. Only the first interrupt has any effect. Subsequent interrupts are ignored. Interrupts are also ignored until enabled. If interrupting the command requires an attention signal to be sent to the server, then this method sends that signal if the command's request is already complete. Signalling mechanism is "fire and forget". It is up to either the execution thread or, possibly, a detaching thread, to ensure that any pending attention ack later will be received and processed.
 * @param reason the reason for the interrupt, typically cancel or timeout.
 * @throws SQLServerException if interrupting fails for some reason. This call does not throw the reason for the interrupt.
 */
void interrupt(String reason) throws SQLServerException {
synchronized (interruptLock) {
    if (interruptsEnabled && !wasInterrupted()) {
      if (logger.isLoggable(Level.FINEST))       logger.finest(this + ": Raising interrupt for reason:" + reason);
      wasInterrupted=true;
      interruptReason=reason;
      if (requestComplete)       attentionPending=tdsWriter.sendAttention();
    }
  }
}

/** 
 * @param atLeastOfKind use {@link MessageKind#ERROR} to filter out warnings and info, use {@link MessageKind#WARNING} to show errors and warnings only.Use of  {@link MessageKind#INFORMATION} makes no sense and effectively a no-op.
 * @return a new handler that would receive messages of the specified or severer) kind only
 */
default IMessageHandler restrict(@NotNull final MessageKind atLeastOfKind){
  if (atLeastOfKind == MessageKind.INFORMATION) {
    assert MessageKind.values().length == 3 : "Just in case someone adds new values there, and the assumption INFO is lowest possible level breaks";
    return IMessageHandler.this;
  }
  return msg -> {
    if (msg.getKind().isSameOrGreaterSeverityThan(atLeastOfKind)) {
      IMessageHandler.this.handle(msg);
    }
  }
;
}

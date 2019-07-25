@Override public IMessageHandler restrict(@NotNull MessageKind atLeastOfKind){
  setWarningsEnabled(MessageKind.WARNING.isSameOrGreaterSeverityThan(atLeastOfKind));
  setInfoEnabled(MessageKind.INFORMATION.isSameOrGreaterSeverityThan(atLeastOfKind));
  return this;
}

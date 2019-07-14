private void handleSession(final SessionContext context){
  for (  Setting setting : settings) {
    if (setting.match(context.getRequest())) {
      setting.writeToResponse(context);
      return;
    }
  }
  if (anySetting.match(context.getRequest())) {
    anySetting.writeToResponse(context);
    return;
  }
  this.monitor.onUnexpectedMessage(context.getRequest());
  throw new MocoException(format("No handler found for request: %s",context.getRequest().getContent()));
}

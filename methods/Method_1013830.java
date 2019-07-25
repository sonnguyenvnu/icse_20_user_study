@Override public void update(Object args,Observable observable){
  if (!(observable instanceof Tunnel)) {
    logger.error("[update] should observe tunnel only, not {}",observable.getClass().getName());
    return;
  }
  DefaultTunnel tunnel=(DefaultTunnel)observable;
  if (!(args instanceof TunnelStateChangeEvent)) {
    return;
  }
  TunnelStateChangeEvent event=(TunnelStateChangeEvent)args;
  TunnelState current=event.getCurrent();
  if (current instanceof TunnelEstablished) {
    onEstablished();
  }
 else   if (current instanceof FrontendClosed) {
    onFrontendClose();
  }
 else   if (current instanceof BackendClosed) {
    onBackendClose();
  }
 else   if (current instanceof TunnelClosing) {
    onClosing();
  }
 else   if (current instanceof TunnelClosed) {
    onClosed();
  }
}

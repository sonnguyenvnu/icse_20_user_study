@Override protected void init(ReconnectView reconnectView){
  view.setPresenter(this);
  view.setReconnectView(reconnectView);
  connect(resolveService());
}

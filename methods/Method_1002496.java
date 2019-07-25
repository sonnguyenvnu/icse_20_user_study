public TransportDispatcherBuilder reset(){
  _restHandlers.clear();
  _adaptedHandlers.clear();
  _streamHandlers.clear();
  return this;
}

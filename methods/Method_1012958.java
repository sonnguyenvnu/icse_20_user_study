public PickleHandler create(Socket socket){
  return new PickleHandler(socket,executor,checksStore,checkRunnerFactory);
}

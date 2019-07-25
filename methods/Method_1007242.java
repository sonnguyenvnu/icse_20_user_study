public void disconnect(){
  final boolean res=assignState(SessionStatus.CONNECTED,SessionStatus.DISCONNECTING);
  if (!res) {
    return;
  }
  mqttConnection=null;
  will=null;
  assignState(SessionStatus.DISCONNECTING,SessionStatus.DISCONNECTED);
}

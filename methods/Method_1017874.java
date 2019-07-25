@Override public void accept(Event<Alert> event){
  final Alert alert=unwrapAlert(event.getData());
  final AlertManager mgr=(AlertManager)alert.getSource();
  final List<AlertResponder> responders=snapshotResponderts();
  responders.forEach(responder -> {
    AlertResponse resp=mgr.getResponse(alert);
    AlertResponseWrapper wrapper=new AlertResponseWrapper(resp);
    responder.alertChange(alert,wrapper);
  }
);
}

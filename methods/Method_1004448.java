@Override public boolean process(ClientRegisterProcessor.ClientRegisterMessage message,ActorSystem.Actor<ClientRegisterProcessor.ClientRegisterMessage> self){
  final MetaInfoRequest request=message.getMetaInfoRequest();
  final MetaInfoResponse response=handleClientRegister(request);
  writeResponse(message,response);
  return true;
}

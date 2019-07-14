@Override protected void updateServerConfig(){
  final SharedPreferences preferences=MessagesController.getMainSettings(currentAccount);
  VoIPServerConfig.setConfig(preferences.getString("voip_server_config","{}"));
  ConnectionsManager.getInstance(currentAccount).sendRequest(new TLRPC.TL_phone_getCallConfig(),new RequestDelegate(){
    @Override public void run(    TLObject response,    TLRPC.TL_error error){
      if (error == null) {
        String data=((TLRPC.TL_dataJSON)response).data;
        VoIPServerConfig.setConfig(data);
        preferences.edit().putString("voip_server_config",data).commit();
      }
    }
  }
);
}

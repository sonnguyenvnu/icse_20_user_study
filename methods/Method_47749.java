public void onNetworkStatusChanged(boolean isConnected){
  if (!isListening)   return;
  if (isConnected)   socket.connect();
 else   socket.disconnect();
}

/** 
 * ( begin auto-generated from Server_disconnect.xml ) Disconnect a particular client. ( end auto-generated )
 * @brief Disconnect a particular client.
 * @webref server:server
 * @param client the client to disconnect
 */
public void disconnect(Client client){
  client.stop();
synchronized (clientsLock) {
    int index=clientIndex(client);
    if (index != -1) {
      removeIndex(index);
    }
  }
}

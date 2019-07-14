/** 
 * ( begin auto-generated from Server_available.xml ) Returns the next client in line with a new message. ( end auto-generated )
 * @brief Returns the next client in line with a new message.
 * @webref server
 * @usage application
 */
public Client available(){
synchronized (clientsLock) {
    int index=lastAvailable + 1;
    if (index >= clientCount)     index=0;
    for (int i=0; i < clientCount; i++) {
      int which=(index + i) % clientCount;
      Client client=clients[which];
      if (!client.active()) {
        removeIndex(which);
        i--;
        which--;
      }
      if (client.available() > 0) {
        lastAvailable=which;
        return client;
      }
    }
  }
  return null;
}

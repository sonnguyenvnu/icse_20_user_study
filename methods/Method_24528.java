/** 
 * ( begin auto-generated from Server_write.xml ) Writes a value to all the connected clients. It sends bytes out from the  Server object. ( end auto-generated )
 * @webref server
 * @brief Writes data to all connected clients
 * @param data data to write
 */
public void write(int data){
synchronized (clientsLock) {
    int index=0;
    while (index < clientCount) {
      if (clients[index].active()) {
        clients[index].write(data);
        index++;
      }
 else {
        removeIndex(index);
      }
    }
  }
}

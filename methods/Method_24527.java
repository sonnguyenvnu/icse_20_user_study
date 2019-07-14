/** 
 * Disconnect all clients and stop the server: internal use only.
 */
public void dispose(){
  thread=null;
  if (clients != null) {
    disconnectAll();
    clientCount=0;
    clients=null;
  }
  try {
    if (server != null) {
      server.close();
      server=null;
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}

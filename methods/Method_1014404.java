/** 
 * start the owserver connection
 */
public void start(){
  connectionErrorCounter=0;
  owserverConnectionState=OwserverConnectionState.CLOSED;
  boolean success=false;
  do {
    success=open();
  }
 while (success != true && owserverConnectionState != OwserverConnectionState.FAILED);
}

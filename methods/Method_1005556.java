/** 
 * Receive notification that we are offline<br> if cleanSession is true, we need to regard this as a disconnection
 */
void offline(){
  if (!disconnected && !cleanSession) {
    Exception e=new Exception("Android offline");
    connectionLost(e);
  }
}

@Override public String status(){
  if (channel == null)   return "n/a";
  if (isConnected())   return "connected";
  if (isConnectionPending())   return "connection pending";
  if (isOpen())   return "open";
  return "closed";
}

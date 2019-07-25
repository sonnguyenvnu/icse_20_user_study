@Override public String status(){
  if (sock == null)   return "n/a";
  if (isConnected())   return "connected";
  if (isOpen())   return "open";
  return "closed";
}

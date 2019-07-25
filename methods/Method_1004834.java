private synchronized InflightMessage head(){
  Iterator<InflightMessage> it=iterator();
  if (it.hasNext())   return it.next();
 else   return null;
}

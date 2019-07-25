protected void queue(Message msg){
  Address dest=msg.dest();
  Map<Address,List<Message>> map;
  if (dest == null)   map=msg.isFlagSet(Message.Flag.OOB) ? oob_map_mcast : reg_map_mcast;
 else   map=msg.isFlagSet(Message.Flag.OOB) ? oob_map_ucast : reg_map_ucast;
  Address sender=msg.src();
synchronized (map) {
    List<Message> list=map.get(sender);
    if (list == null)     map.put(sender,list=new ArrayList<>());
    list.add(msg);
  }
}

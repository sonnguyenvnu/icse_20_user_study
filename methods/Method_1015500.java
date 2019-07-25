public Object down(Message msg){
  boolean copy=(copy_multicast_msgs || copy_unicast_msgs) && outgoing_copies > 0;
  if (!copy)   return down_prot.down(msg);
  copy(msg,outgoing_copies,Direction.DOWN);
  return down_prot.down(msg);
}

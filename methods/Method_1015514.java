protected void send(Address target,long ack_id,byte type){
  down_prot.down(new Message(target).putHeader(id,new ForwardHeader(type,ack_id)));
}

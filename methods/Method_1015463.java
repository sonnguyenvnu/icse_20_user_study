public Object down(Message msg){
  FORK.ForkHeader hdr=msg.getHeader(FORK.ID);
  if (hdr == null)   msg.putHeader(FORK.ID,hdr=new FORK.ForkHeader(fork_stack_id,null));
 else   hdr.setForkStackId(fork_stack_id);
  return down_prot.down(msg);
}

protected void retransmit(long first_seqno,long last_seqno,final Address sender,boolean multicast_xmit_request){
  SeqnoList list=new SeqnoList((int)(last_seqno - first_seqno + 1),first_seqno).add(first_seqno,last_seqno);
  retransmit(list,sender,multicast_xmit_request);
}

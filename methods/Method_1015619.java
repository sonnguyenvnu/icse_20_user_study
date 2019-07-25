public UnicastHeader3 copy(){
  return new UnicastHeader3(type,seqno,conn_id,first);
}

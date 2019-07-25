protected void update(Entry entry,int num_received){
  if (conn_expiry_timeout > 0)   entry.update();
  if (entry.state() == State.CLOSING)   entry.state(State.OPEN);
  num_msgs_received+=num_received;
}

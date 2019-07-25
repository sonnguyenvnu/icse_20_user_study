public void receive(Message msg){
  String line=msg.getSrc() + ": " + msg.getObject();
  System.out.println(line);
synchronized (state) {
    state.add(line);
  }
}

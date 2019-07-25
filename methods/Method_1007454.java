public void execute() throws Pausable {
  while (true) {
    Integer val=mymb.get();
    if (nextmb == null) {
      numReceived++;
      if (numReceived == nMsgs) {
        done();
        break;
      }
    }
 else {
      nextmb.put(val);
    }
  }
}

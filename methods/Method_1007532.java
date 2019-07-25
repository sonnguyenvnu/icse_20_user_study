public ExitMsg<TT> join() throws Pausable {
  Mailbox<ExitMsg<TT>> mb=new Mailbox();
  informOnExit(mb);
  return mb.get();
}

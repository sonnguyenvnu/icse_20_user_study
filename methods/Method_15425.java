public DemoParser setSession(HttpSession session){
  this.session=session;
  setVisitor(DemoVerifier.getVisitor(session));
  return this;
}

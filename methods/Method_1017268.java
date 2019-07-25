public void register(Member member) throws Exception {
  log.info("Registering " + member.getName());
  Session session=(Session)em.getDelegate();
  session.persist(member);
  memberEventSrc.fire(member);
}

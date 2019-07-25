public void register(Member member) throws Exception {
  log.info("Registering " + member.getName());
  em.persist(member);
  memberEventSrc.fire(member);
}

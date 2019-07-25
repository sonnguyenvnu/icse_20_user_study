@Override public String process() throws Exception {
  long contacts=(long)entityManager.createQuery("SELECT COUNT(c) FROM Contact c").getSingleResult();
  log.info("Imported " + contacts + " contacts into the database.");
  return "END";
}

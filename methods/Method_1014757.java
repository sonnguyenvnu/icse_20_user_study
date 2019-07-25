@Override public void run(String... args) throws Exception {
  logger.info("init......createIfNotExistsTable");
  userMapper.createIfNotExistsTable();
  for (int i=0; i < 10; i++) {
    messageService.send("message" + i);
  }
}

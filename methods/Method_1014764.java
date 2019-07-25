@Override public void run(String... args) throws Exception {
  logger.info(".... Fetching books");
  logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
  logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
  logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
  logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
  logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
  logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
}

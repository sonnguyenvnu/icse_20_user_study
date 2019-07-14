public void removeClientDetails(String clientId) throws NoSuchClientException {
  int count=jdbcTemplate.update(deleteClientDetailsSql,clientId);
  if (count != 1) {
    throw new NoSuchClientException("No client found with id = " + clientId);
  }
}

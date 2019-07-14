public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
  ClientDetails details;
  try {
    details=jdbcTemplate.queryForObject(selectClientDetailsSql,new ClientDetailsRowMapper(),clientId);
  }
 catch (  EmptyResultDataAccessException e) {
    throw new NoSuchClientException("No client with requested id: " + clientId);
  }
  return details;
}

@Override public Optional<ClientOfflineState> select(String clientId,String subject,String consumerGroup){
  List<ClientOfflineState> list=jdbcTemplate.query(SELECT_SQL,ROW_MAPPER,clientId,subject,consumerGroup);
  return list != null && !list.isEmpty() ? Optional.ofNullable(list.get(0)) : Optional.empty();
}

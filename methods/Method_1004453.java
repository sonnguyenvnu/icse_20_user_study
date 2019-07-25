@Override public int insert(final ReadonlyBrokerGroupSetting setting){
  return jdbcTemplate.update(INSERT_SQL,setting.getSubject(),setting.getBrokerGroup());
}

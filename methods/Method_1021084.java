@Override @Transactional public void update(final EventType eventType) throws InternalNakadiException {
  try {
    final String sql="SELECT et_event_type_object -> 'schema' ->> 'version' " + "FROM zn_data.event_type WHERE et_name = ?";
    final String currentVersion=jdbcTemplate.queryForObject(sql,String.class,eventType.getName());
    if (!currentVersion.equals(eventType.getSchema().getVersion().toString())) {
      insertEventTypeSchema(eventType);
    }
    jdbcTemplate.update("UPDATE zn_data.event_type SET et_event_type_object = ?::jsonb WHERE et_name = ?",jsonMapper.writer().writeValueAsString(eventType),eventType.getName());
  }
 catch (  JsonProcessingException e) {
    throw new InternalNakadiException("Serialization problem during persistence of event type \"" + eventType.getName() + "\"",e);
  }
}

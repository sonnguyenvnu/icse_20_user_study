@Override public List<EventType> list(){
  return jdbcTemplate.query("SELECT et_event_type_object FROM zn_data.event_type",new EventTypeMapper());
}

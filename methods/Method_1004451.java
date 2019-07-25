@Override public long now(){
  List<Long> results=jdbcTemplate.query(SELECT_NOW_SQL,(rs,rowNum) -> {
    try {
      return rs.getTimestamp("ts").getTime();
    }
 catch (    Exception e) {
      LOGGER.error("select now exception",e);
      return (long)-1;
    }
  }
);
  return results != null && !results.isEmpty() ? results.get(0) : -1;
}

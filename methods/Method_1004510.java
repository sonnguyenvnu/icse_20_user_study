@Override public int elect(String name,String node,long lastSeenActive,long maxLeaderErrorTime){
  return jdbcTemplate.update("update leader_election set node = ?, last_seen_active = unix_timestamp(now(3)) * 1000 " + " where `name` = ? and last_seen_active = ? and ( (? <  (unix_timestamp(now(3)) * 1000 - ?)) or node = '')",node,name,lastSeenActive,lastSeenActive,maxLeaderErrorTime);
}

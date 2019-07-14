public void invalidateNewerThan(Timestamp timestamp){
  getScores().invalidateNewerThan(timestamp);
  getCheckmarks().invalidateNewerThan(timestamp);
  getStreaks().invalidateNewerThan(timestamp);
}

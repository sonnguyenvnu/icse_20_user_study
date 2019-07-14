public int getDefaultScoreSpinnerPosition(){
  int defaultScoreInterval=storage.getInt("pref_score_view_interval",1);
  if (defaultScoreInterval > 5 || defaultScoreInterval < 0) {
    defaultScoreInterval=1;
    storage.putInt("pref_score_view_interval",1);
  }
  return defaultScoreInterval;
}

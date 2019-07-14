public void updateLastHint(int number,Timestamp timestamp){
  storage.putInt("last_hint_number",number);
  storage.putLong("last_hint_timestamp",timestamp.getUnixTime());
}

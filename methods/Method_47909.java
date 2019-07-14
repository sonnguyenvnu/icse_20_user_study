public Timestamp plus(int days){
  return new Timestamp(unixTime + DAY_LENGTH * days);
}

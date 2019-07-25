@Override public void write(final String database,final String retentionPolicy,final ConsistencyLevel consistency,final String records){
  write(database,retentionPolicy,consistency,TimeUnit.NANOSECONDS,records);
}

public void backup(String dir) throws RocksDBException {
  Checkpoint cp=Checkpoint.create(database);
  cp.createCheckpoint(dir + this.getDBName());
}

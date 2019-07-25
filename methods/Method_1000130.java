private void retreat(){
  dbs.forEach(db -> db.setHead(db.getHead().retreat()));
  --size;
}

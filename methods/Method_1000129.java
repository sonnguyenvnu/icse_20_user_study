private void advance(){
  dbs.forEach(db -> db.setHead(db.getHead().advance()));
  ++size;
}

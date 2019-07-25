@Override void add(Database database,Value v){
  if (all || v != ValueNull.INSTANCE) {
    count++;
  }
}

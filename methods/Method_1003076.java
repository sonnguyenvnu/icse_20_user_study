@Override void add(Database database,Value v){
  if (v == ValueNull.INSTANCE) {
    return;
  }
  Collection<Value> c=values;
  if (c == null) {
    values=c=distinct ? new TreeSet<>(database.getCompareMode()) : new ArrayList<Value>();
  }
  c.add(v);
}

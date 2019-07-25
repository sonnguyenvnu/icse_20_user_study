public void set(final String table,final String key,String value) throws IOException {
  if (table != null) {
    final Map<String,String> l=tables.get(table);
    if (l == null)     throw new RuntimeException("Microtables.set: table does not exist");
    if (value == null)     value="";
    l.put(key,value);
  }
  if (this.propFile != null)   commit(false);
}

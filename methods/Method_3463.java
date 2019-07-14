@Override public void save(DataOutputStream out) throws Exception {
  out.writeInt(total);
  Integer[] valueArray=d.getValueArray(new Integer[0]);
  out.writeInt(valueArray.length);
  for (  Integer v : valueArray) {
    out.writeInt(v);
  }
  d.save(out);
}

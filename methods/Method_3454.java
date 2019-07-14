@Override public void save(DataOutputStream out) throws Exception {
  out.writeDouble(l1);
  out.writeDouble(l2);
  out.writeDouble(l3);
  tf.save(out);
}

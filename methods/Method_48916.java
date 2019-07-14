@Override public JointIndexQuery updateLimit(int newLimit){
  JointIndexQuery ji=new JointIndexQuery(Lists.newArrayList(queries));
  ji.setLimit(newLimit);
  return ji;
}

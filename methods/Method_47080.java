@Override protected void initRandomFactory(boolean ignored){
  setRandomFactory(new SingletonRandomFactory(new JCERandom.Factory()));
}

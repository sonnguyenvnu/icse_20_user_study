@Override public void reset(){
  ThreadLocalUtils.remove(DefaultTableSwitcher.class.getName() + "_current");
}

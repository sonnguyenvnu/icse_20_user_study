public int test() throws Pausable {
  int m=10;
  for (int i=0; i < 2; i++) {
    Task.sleep(100);
    m*=2;
  }
  return m;
}

@Override public void trim(){
  if (valueslength.length == 2 * nbrruns) {
    return;
  }
  valueslength=Arrays.copyOf(valueslength,2 * nbrruns);
}

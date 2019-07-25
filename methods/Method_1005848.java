@Override public boolean insert(Setting setting) throws MyException {
  if (setting == null) {
    return false;
  }
  return super.insert(setting);
}

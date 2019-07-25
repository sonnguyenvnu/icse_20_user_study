@Override public Email clone() throws CloneNotSupportedException {
  return new Email(account,host);
}

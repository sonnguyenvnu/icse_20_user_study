@Override public String toString(){
  return this.wasConfigured ? Json.write(this) : "(no response definition configured)";
}

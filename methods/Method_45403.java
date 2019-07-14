@Override public String getOperation(){
  if (this.content == null) {
    return null;
  }
  return this.content.getOperation();
}

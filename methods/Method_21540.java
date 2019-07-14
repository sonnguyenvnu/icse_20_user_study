@Override protected Object clone() throws CloneNotSupportedException {
  return new Field(new String(this.name),new String(this.alias));
}

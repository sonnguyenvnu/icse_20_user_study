public void add(final String key) throws IOException {
  if (this.keys.containsKey(key))   return;
synchronized (this.raf) {
    if (this.keys.containsKey(key))     return;
    this.keys.put(key,_obj);
    this.raf.seek(this.raf.length());
    this.raf.write(UTF8.getBytes(key));
    this.raf.writeByte('\n');
  }
}

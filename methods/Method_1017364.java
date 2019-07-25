public synchronized void save(@NotNull DataOutput out,Set<String> value) throws IOException {
  out.writeInt(value.size());
  Iterator var=value.iterator();
  while (var.hasNext()) {
    String s=(String)var.next();
    EnumeratorStringDescriptor.INSTANCE.save(out,s);
  }
}

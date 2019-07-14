public void writeData(Writer writer,Iterator<T> items) throws IOException {
  int count=1;
  StringBuilder buf=new StringBuilder(300);
  T rv;
  final int lastColumnIdx=columns.size() - 1;
  while (items.hasNext()) {
    buf.setLength(0);
    rv=items.next();
    for (int i=0; i < lastColumnIdx; i++) {
      quoteAndCommify(buf,columns.get(i).accessor.get(count,rv,separator));
    }
    quote(buf,columns.get(lastColumnIdx).accessor.get(count,rv,separator));
    buf.append(lineSeparator);
    writer.write(buf.toString());
    count++;
  }
}

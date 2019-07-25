/** 
 * Get the next list of records.
 * @see org.springframework.batch.item.ItemReader#read()
 */
@Override public List<T> read() throws Exception {
  ResultHolder holder=new ResultHolder();
  while (process(itemReader.read(),holder)) {
    continue;
  }
  if (!holder.isExhausted()) {
    return holder.getRecords();
  }
 else {
    return null;
  }
}

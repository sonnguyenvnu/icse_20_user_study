protected <T>GroupRequest<T> cast(final Collection<Address> dests,byte[] data,int offset,int length,RequestOptions options,boolean block_for_results) throws Exception {
  return cast(dests,new Buffer(data,offset,length),options,block_for_results);
}

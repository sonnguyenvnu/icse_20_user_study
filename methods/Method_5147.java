private void maybeExpandData(int limit){
  if (data == null) {
    data=new byte[READ_GRANULARITY];
  }
 else   if (data.length < limit + READ_GRANULARITY) {
    data=Arrays.copyOf(data,data.length + READ_GRANULARITY);
  }
}

@Override public void hmset(final String key,final Map<String,String> hash){
  final Map<byte[],byte[]> bhash=new HashMap<byte[],byte[]>(hash.size());
  for (  final Entry<String,String> entry : hash.entrySet()) {
    bhash.put(SafeEncoder.encode(entry.getKey()),SafeEncoder.encode(entry.getValue()));
  }
  hmset(SafeEncoder.encode(key),bhash);
}

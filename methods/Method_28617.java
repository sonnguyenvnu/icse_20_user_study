public void scan(final byte[] cursor,final ScanParams params){
  final List<byte[]> args=new ArrayList<byte[]>();
  args.add(cursor);
  args.addAll(params.getParams());
  sendCommand(SCAN,args.toArray(new byte[args.size()][]));
}

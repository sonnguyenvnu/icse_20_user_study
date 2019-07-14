@Override public long open(DataSpec dataSpec) throws IOException {
  uri=dataSpec.uri;
  currentAccount=Utilities.parseInt(uri.getQueryParameter("account"));
  parentObject=FileLoader.getInstance(currentAccount).getParentObject(Utilities.parseInt(uri.getQueryParameter("rid")));
  document=new TLRPC.TL_document();
  document.access_hash=Utilities.parseLong(uri.getQueryParameter("hash"));
  document.id=Utilities.parseLong(uri.getQueryParameter("id"));
  document.size=Utilities.parseInt(uri.getQueryParameter("size"));
  document.dc_id=Utilities.parseInt(uri.getQueryParameter("dc"));
  document.mime_type=uri.getQueryParameter("mime");
  document.file_reference=Utilities.hexToBytes(uri.getQueryParameter("reference"));
  TLRPC.TL_documentAttributeFilename filename=new TLRPC.TL_documentAttributeFilename();
  filename.file_name=uri.getQueryParameter("name");
  document.attributes.add(filename);
  if (document.mime_type.startsWith("video")) {
    document.attributes.add(new TLRPC.TL_documentAttributeVideo());
  }
 else   if (document.mime_type.startsWith("audio")) {
    document.attributes.add(new TLRPC.TL_documentAttributeAudio());
  }
  loadOperation=FileLoader.getInstance(currentAccount).loadStreamFile(this,document,parentObject,currentOffset=(int)dataSpec.position);
  bytesRemaining=dataSpec.length == C.LENGTH_UNSET ? document.size - dataSpec.position : dataSpec.length;
  if (bytesRemaining < 0) {
    throw new EOFException();
  }
  opened=true;
  transferStarted(dataSpec);
  if (loadOperation != null) {
    file=new RandomAccessFile(loadOperation.getCurrentFile(),"r");
    file.seek(currentOffset);
  }
  return bytesRemaining;
}

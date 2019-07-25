protected static Buffer marshal(final View view,final Digest digest){
  try {
    int expected_size=Global.SHORT_SIZE;
    if (view != null)     expected_size+=view.serializedSize();
    boolean write_addrs=writeAddresses(view,digest);
    if (digest != null)     expected_size=(int)digest.serializedSize(write_addrs);
    final ByteArrayDataOutputStream out=new ByteArrayDataOutputStream(expected_size + 10);
    out.writeShort(determineFlags(view,digest));
    if (view != null)     view.writeTo(out);
    if (digest != null)     digest.writeTo(out,write_addrs);
    return out.getBuffer();
  }
 catch (  Exception ex) {
    return null;
  }
}

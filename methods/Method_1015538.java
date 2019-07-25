protected static Buffer marshal(final ViewId view_id){
  try {
    final ByteArrayDataOutputStream out=new ByteArrayDataOutputStream(Util.size(view_id));
    Util.writeViewId(view_id,out);
    return out.getBuffer();
  }
 catch (  Exception ex) {
    return null;
  }
}

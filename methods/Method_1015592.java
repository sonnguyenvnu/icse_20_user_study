public Object down(Message msg){
  GmsHeader hdr=msg.getHeader(GMS_ID);
  Address remoteAddress=msg.getDest();
  if (needsAuthentication(hdr,remoteAddress)) {
    SaslClientContext ctx=null;
    try {
      ctx=new SaslClientContext(saslClientFactory,mech,server_name != null ? server_name : remoteAddress.toString(),client_callback_handler,sasl_props,client_subject);
      sasl_context.put(remoteAddress,ctx);
      ctx.addHeader(msg,null);
    }
 catch (    Exception e) {
      if (ctx != null) {
        disposeContext(remoteAddress);
      }
      throw new SecurityException(e);
    }
  }
  return down_prot.down(msg);
}

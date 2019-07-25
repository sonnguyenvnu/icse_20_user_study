public Object down(Message msg){
  GMS.GmsHeader hdr=getGMSHeader(msg);
  if (hdr != null && needsAuthentication(hdr)) {
    msg.putHeader(this.id,new AuthHeader(this.auth_token));
  }
  return down_prot.down(msg);
}

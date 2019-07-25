public static Buffer marshal(JoinRsp join_rsp){
  return Util.streamableToBuffer(join_rsp);
}

@Override public P2pMessage create(byte[] data) throws Exception {
  if (data.length <= 1) {
    throw new P2pException(TypeEnum.MESSAGE_WITH_WRONG_LENGTH,"messageType=" + (data.length == 1 ? data[0] : "unknow"));
  }
  try {
    byte type=data[0];
    byte[] rawData=ArrayUtils.subarray(data,1,data.length);
    return create(type,rawData);
  }
 catch (  Exception e) {
    if (e instanceof P2pException) {
      throw e;
    }
 else {
      throw new P2pException(P2pException.TypeEnum.PARSE_MESSAGE_FAILED,"type=" + data[0] + ", len=" + data.length);
    }
  }
}

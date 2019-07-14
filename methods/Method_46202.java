/** 
 * ?? map ???????, ?????? bolt ? header <p> {@link SofaRpcSerialization#deserializeHeader(com.alipay.remoting.rpc.RequestCommand)}
 * @param bytes bolt header
 * @return ?????? Map ??
 * @throws DeserializationException DeserializationException
 */
public Map<String,String> decode(byte[] bytes) throws DeserializationException {
  Map<String,String> map=new HashMap<String,String>();
  if (bytes == null || bytes.length == 0) {
    return map;
  }
  UnsafeByteArrayInputStream in=new UnsafeByteArrayInputStream(bytes);
  try {
    while (in.available() > 0) {
      int length=readInt(in);
      byte[] key=new byte[length];
      in.read(key);
      length=readInt(in);
      byte[] value=new byte[length];
      in.read(value);
      Charset charset=RpcConstants.DEFAULT_CHARSET;
      map.put(new String(key,charset),new String(value,charset));
    }
    return map;
  }
 catch (  IOException ex) {
    throw new DeserializationException(ex.getMessage(),ex);
  }
}

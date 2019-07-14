/** 
 * ??empty???????
 * @param data ????
 * @param out ???
 * @throws IOException ????
 */
public void writeSupportEmpty(String data,OutputStream out) throws IOException {
  if (StringUtils.isEmpty(data)) {
    writeInt(out,0);
  }
 else {
    byte[] bs=data.getBytes(RpcConstants.DEFAULT_CHARSET);
    writeInt(out,bs.length);
    out.write(bs);
  }
}

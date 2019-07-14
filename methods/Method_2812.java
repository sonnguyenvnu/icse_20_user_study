/** 
 * ?InputStream???????????
 * @param is ?
 * @param targetArray output
 * @return ????????????0?????????
 * @throws IOException
 */
public static int readBytesFromOtherInputStream(InputStream is,byte[] targetArray) throws IOException {
  assert targetArray != null;
  if (targetArray.length == 0)   return 0;
  int len;
  int off=0;
  while (off < targetArray.length && (len=is.read(targetArray,off,targetArray.length - off)) != -1) {
    off+=len;
  }
  return off;
}

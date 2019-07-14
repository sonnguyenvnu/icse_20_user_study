/** 
 * String????? byte ??
 * @param data             String???
 * @param charsetNametypes ???? ???utf-8
 * @return byte ?????DataUtil.string2ByteArrWithCharset("?","utf-16");
 */
public static byte[] string2ByteArr(@NotNull String data,@NotNull String charsetNametypes) throws Exception {
  if (charsetNametypes == null || charsetNametypes.equals("")) {
    return data.getBytes("utf-8");
  }
 else {
    if (charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_UTF8) || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_UTF16) || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_UTF32) || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_UNICODE) || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_GBK) || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_GB2312) || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_GB18030) || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_ASCII) || charsetNametypes.equals(CharsetNameTypes.CHARSETNAME_ISO_8859_1)) {
      return data.getBytes(charsetNametypes);
    }
 else {
      throw new IllegalAccessException("???????????????");
    }
  }
}

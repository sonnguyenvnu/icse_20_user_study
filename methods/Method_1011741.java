public static byte[] convert(byte[] array){
  return new String(array,FileUtil.DEFAULT_CHARSET).replace("\r\n","\n").getBytes(FileUtil.DEFAULT_CHARSET);
}

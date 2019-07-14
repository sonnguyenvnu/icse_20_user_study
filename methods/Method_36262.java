private static boolean isExtendedWebp(byte[] header){
  return header[12] == 'V' && header[13] == 'P' && header[14] == '8' && header[15] == 'X';
}

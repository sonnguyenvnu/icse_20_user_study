/** 
 * Create a CRC16 checksum from the bytes. implementation is from mp911de/lettuce, modified with some more optimizations
 * @param bytes
 * @return CRC16 as integer value See <ahref="https://github.com/xetorthio/jedis/pull/733#issuecomment-55840331">Issue 733</a>
 */
public static int getCRC16(byte[] bytes,int s,int e){
  int crc=0x0000;
  for (int i=s; i < e; i++) {
    crc=((crc << 8) ^ LOOKUP_TABLE[((crc >>> 8) ^ (bytes[i] & 0xFF)) & 0xFF]);
  }
  return crc & 0xFFFF;
}

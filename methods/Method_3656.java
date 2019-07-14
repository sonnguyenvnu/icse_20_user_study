/** 
 * ???????
 * @param out
 * @param uint
 * @throws IOException
 */
public static void writeUnsignedInt(DataOutputStream out,int uint) throws IOException {
  out.writeByte((byte)((uint >>> 8) & 0xFF));
  out.writeByte((byte)((uint >>> 0) & 0xFF));
}

private static void overwrite(ByteBuffer buf,Entry e){
  buf.position(buf.position() - ENTRY_SIZE);
  buf.putInt(e.hash);
  buf.putInt((int)(e.offset >> 16));
  buf.putShort((short)(e.offset & 0xFFFF));
}

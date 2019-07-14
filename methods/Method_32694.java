/** 
 * Millisecond encoding formats: upper two bits  units       field length  approximate range --------------------------------------------------------------- 00              30 minutes  1 byte        +/- 16 hours 01              minutes     4 bytes       +/- 1020 years 10              seconds     5 bytes       +/- 4355 years 11              millis      9 bytes       +/- 292,000,000 years Remaining bits in field form signed offset from 1970-01-01T00:00:00Z.
 */
static void writeMillis(DataOutput out,long millis) throws IOException {
  if (millis % (30 * 60000L) == 0) {
    long units=millis / (30 * 60000L);
    if (((units << (64 - 6)) >> (64 - 6)) == units) {
      out.writeByte((int)(units & 0x3f));
      return;
    }
  }
  if (millis % 60000L == 0) {
    long minutes=millis / 60000L;
    if (((minutes << (64 - 30)) >> (64 - 30)) == minutes) {
      out.writeInt(0x40000000 | (int)(minutes & 0x3fffffff));
      return;
    }
  }
  if (millis % 1000L == 0) {
    long seconds=millis / 1000L;
    if (((seconds << (64 - 38)) >> (64 - 38)) == seconds) {
      out.writeByte(0x80 | (int)((seconds >> 32) & 0x3f));
      out.writeInt((int)(seconds & 0xffffffff));
      return;
    }
  }
  out.writeByte(millis < 0 ? 0xff : 0xc0);
  out.writeLong(millis);
}

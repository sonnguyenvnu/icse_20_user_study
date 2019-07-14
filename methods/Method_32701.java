/** 
 * Encodes a built DateTimeZone to the given stream. Call readFrom to decode the data into a DateTimeZone object.
 * @param out  the output stream to receive the encoded DateTimeZone
 * @since 1.5 (parameter added)
 */
public void writeTo(String zoneID,DataOutput out) throws IOException {
  DateTimeZone zone=toDateTimeZone(zoneID,false);
  if (zone instanceof FixedDateTimeZone) {
    out.writeByte('F');
    out.writeUTF(zone.getNameKey(0));
    writeMillis(out,zone.getOffset(0));
    writeMillis(out,zone.getStandardOffset(0));
  }
 else {
    if (zone instanceof CachedDateTimeZone) {
      out.writeByte('C');
      zone=((CachedDateTimeZone)zone).getUncachedZone();
    }
 else {
      out.writeByte('P');
    }
    ((PrecalculatedZone)zone).writeTo(out);
  }
}

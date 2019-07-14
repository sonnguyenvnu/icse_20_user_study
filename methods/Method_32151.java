/** 
 * By default, when DateTimeZones are serialized, only a "stub" object referring to the id is written out. When the stub is read in, it replaces itself with a DateTimeZone object.
 * @return a stub object to go in the stream
 */
protected Object writeReplace() throws ObjectStreamException {
  return new Stub(iID);
}

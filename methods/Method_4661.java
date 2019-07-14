/** 
 * Builds a PSSH atom for the given system id, containing the given key ids and data.
 * @param systemId The system id of the scheme.
 * @param keyIds The key ids for a version 1 PSSH atom, or null for a version 0 PSSH atom.
 * @param data The scheme specific data.
 * @return The PSSH atom.
 */
@SuppressWarnings("ParameterNotNullable") public static byte[] buildPsshAtom(UUID systemId,@Nullable UUID[] keyIds,@Nullable byte[] data){
  int dataLength=data != null ? data.length : 0;
  int psshBoxLength=Atom.FULL_HEADER_SIZE + 16 + 4 + dataLength;
  if (keyIds != null) {
    psshBoxLength+=4 + (keyIds.length * 16);
  }
  ByteBuffer psshBox=ByteBuffer.allocate(psshBoxLength);
  psshBox.putInt(psshBoxLength);
  psshBox.putInt(Atom.TYPE_pssh);
  psshBox.putInt(keyIds != null ? 0x01000000 : 0);
  psshBox.putLong(systemId.getMostSignificantBits());
  psshBox.putLong(systemId.getLeastSignificantBits());
  if (keyIds != null) {
    psshBox.putInt(keyIds.length);
    for (    UUID keyId : keyIds) {
      psshBox.putLong(keyId.getMostSignificantBits());
      psshBox.putLong(keyId.getLeastSignificantBits());
    }
  }
  if (data != null && data.length != 0) {
    psshBox.putInt(data.length);
    psshBox.put(data);
  }
  return psshBox.array();
}

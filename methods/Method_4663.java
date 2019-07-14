/** 
 * Parses the scheme specific data from a PSSH atom. Version 0 and 1 PSSH atoms are supported. <p>The scheme specific data is only parsed if the data is a valid PSSH atom matching the given UUID, or if the data is a valid PSSH atom of any type in the case that the passed UUID is null.
 * @param atom The atom to parse.
 * @param uuid The required UUID of the PSSH atom, or null to accept any UUID.
 * @return The parsed scheme specific data. Null if the input is not a valid PSSH atom, or if thePSSH atom has an unsupported version, or if the PSSH atom does not match the passed UUID.
 */
public static @Nullable byte[] parseSchemeSpecificData(byte[] atom,UUID uuid){
  PsshAtom parsedAtom=parsePsshAtom(atom);
  if (parsedAtom == null) {
    return null;
  }
  if (uuid != null && !uuid.equals(parsedAtom.uuid)) {
    Log.w(TAG,"UUID mismatch. Expected: " + uuid + ", got: " + parsedAtom.uuid + ".");
    return null;
  }
  return parsedAtom.schemeData;
}

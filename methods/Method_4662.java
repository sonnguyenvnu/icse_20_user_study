/** 
 * Parses the UUID from a PSSH atom. Version 0 and 1 PSSH atoms are supported. <p>The UUID is only parsed if the data is a valid PSSH atom.
 * @param atom The atom to parse.
 * @return The parsed UUID. Null if the input is not a valid PSSH atom, or if the PSSH atom has anunsupported version.
 */
public static @Nullable UUID parseUuid(byte[] atom){
  PsshAtom parsedAtom=parsePsshAtom(atom);
  if (parsedAtom == null) {
    return null;
  }
  return parsedAtom.uuid;
}

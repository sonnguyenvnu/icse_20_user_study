/** 
 * Returns whether the extractor should decode a container atom with type  {@code atom}. 
 */
private static boolean shouldParseContainerAtom(int atom){
  return atom == Atom.TYPE_moov || atom == Atom.TYPE_trak || atom == Atom.TYPE_mdia || atom == Atom.TYPE_minf || atom == Atom.TYPE_stbl || atom == Atom.TYPE_edts || atom == Atom.TYPE_meta;
}

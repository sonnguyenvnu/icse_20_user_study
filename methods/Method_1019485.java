/** 
 * Heapify takes the sketch image in Memory and instantiates an on-heap sketch. The resulting sketch will not retain any link to the source Memory.
 * @param mem a Memory image of a sketch.<a href=" {@docRoot}/resources/dictionary.html#mem">See Memory</a>
 * @return a heap-based sketch based on the given Memory
 */
public static KllFloatsSketch heapify(final Memory mem){
  final int preambleInts=mem.getByte(PREAMBLE_INTS_BYTE) & 0xff;
  final int serialVersion=mem.getByte(SER_VER_BYTE) & 0xff;
  final int family=mem.getByte(FAMILY_BYTE) & 0xff;
  final int flags=mem.getByte(FLAGS_BYTE) & 0xff;
  final int m=mem.getByte(M_BYTE) & 0xff;
  if (m != DEFAULT_M) {
    throw new SketchesArgumentException("Possible corruption: M must be " + DEFAULT_M + ": " + m);
  }
  final boolean isEmpty=(flags & (1 << Flags.IS_EMPTY.ordinal())) > 0;
  final boolean isSingleItem=(flags & (1 << Flags.IS_SINGLE_ITEM.ordinal())) > 0;
  if (isEmpty || isSingleItem) {
    if (preambleInts != PREAMBLE_INTS_SHORT) {
      throw new SketchesArgumentException("Possible corruption: preambleInts must be " + PREAMBLE_INTS_SHORT + " for an empty or single item sketch: " + preambleInts);
    }
  }
 else {
    if (preambleInts != PREAMBLE_INTS_FULL) {
      throw new SketchesArgumentException("Possible corruption: preambleInts must be " + PREAMBLE_INTS_FULL + " for a sketch with more than one item: " + preambleInts);
    }
  }
  if ((serialVersion != serialVersionUID1) && (serialVersion != serialVersionUID2)) {
    throw new SketchesArgumentException("Possible corruption: serial version mismatch: expected " + serialVersionUID1 + " or " + serialVersionUID2 + ", got " + serialVersion);
  }
  if (family != Family.KLL.getID()) {
    throw new SketchesArgumentException("Possible corruption: family mismatch: expected " + Family.KLL.getID() + ", got " + family);
  }
  return new KllFloatsSketch(mem);
}

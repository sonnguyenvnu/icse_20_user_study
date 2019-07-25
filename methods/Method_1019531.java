/** 
 * Wrap takes the sketch image in Memory and refers to it directly. There is no data copying onto the java heap. Only "Direct" Serialization Version 3 (i.e, OpenSource) sketches that have been explicitly stored as direct objects can be wrapped. An attempt to "wrap" earlier version sketches will result in a "heapified", normal Java Heap version of the sketch where all data will be copied to the heap.
 * @param srcMem an image of a Sketch where the image seed hash matches the given seed hash.<a href=" {@docRoot}/resources/dictionary.html#mem">See Memory</a>
 * @param seed <a href="{@docRoot}/resources/dictionary.html#seed">See Update Hash Seed</a>. Compact sketches store a 16-bit hash of the seed, but not the seed itself.
 * @return a UpdateSketch backed by the given Memory
 */
public static UpdateSketch wrap(final WritableMemory srcMem,final long seed){
  final int preLongs=srcMem.getByte(PREAMBLE_LONGS_BYTE) & 0X3F;
  final int serVer=srcMem.getByte(SER_VER_BYTE) & 0XFF;
  final int familyID=srcMem.getByte(FAMILY_BYTE) & 0XFF;
  final Family family=Family.idToFamily(familyID);
  if (family != Family.QUICKSELECT) {
    throw new SketchesArgumentException("A " + family + " sketch cannot be wrapped as an UpdateSketch.");
  }
  if ((serVer == 3) && (preLongs == 3)) {
    return DirectQuickSelectSketch.writableWrap(srcMem,seed);
  }
 else {
    throw new SketchesArgumentException("Corrupted: An UpdateSketch image: must have SerVer = 3 and preLongs = 3");
  }
}

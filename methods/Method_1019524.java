/** 
 * Wrap takes the SetOperation image in Memory and refers to it directly. There is no data copying onto the java heap. Only "Direct" SetOperations that have been explicitly stored as direct can be wrapped.
 * @param srcMem an image of a SetOperation where the hash of the given seed matches the image seed hash.<a href=" {@docRoot}/resources/dictionary.html#mem">See Memory</a>
 * @param seed <a href="{@docRoot}/resources/dictionary.html#seed">See Update Hash Seed</a>.
 * @return a SetOperation backed by the given Memory
 */
public static SetOperation wrap(final Memory srcMem,final long seed){
  final byte famID=srcMem.getByte(FAMILY_BYTE);
  final Family family=idToFamily(famID);
  final int serVer=srcMem.getByte(SER_VER_BYTE);
  if (serVer != 3) {
    throw new SketchesArgumentException("SerVer must be 3: " + serVer);
  }
switch (family) {
case UNION:
{
      return UnionImpl.wrapInstance(srcMem,seed);
    }
case INTERSECTION:
{
    return IntersectionImplR.wrapInstance(srcMem,seed);
  }
default :
throw new SketchesArgumentException("SetOperation cannot wrap family: " + family.toString());
}
}

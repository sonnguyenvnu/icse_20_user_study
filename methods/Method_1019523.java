/** 
 * Wrap takes the SetOperation image in Memory and refers to it directly. There is no data copying onto the java heap. Only "Direct" SetOperations that have been explicitly stored as direct can be wrapped. This method assumes the <a href=" {@docRoot}/resources/dictionary.html#defaultUpdateSeed">Default Update Seed</a>.
 * @param srcMem an image of a SetOperation where the image seed hash matches the default seed hash.<a href=" {@docRoot}/resources/dictionary.html#mem">See Memory</a>
 * @return a SetOperation backed by the given Memory
 */
public static SetOperation wrap(final Memory srcMem){
  return wrap(srcMem,DEFAULT_UPDATE_SEED);
}

/** 
 * Gets whether to decode all the frames and store them in memory. This should only ever be used for animations that are known to be small (e.g. stickers). Caching dozens of large Bitmaps in memory for general GIFs or WebP's will not fit in memory.
 * @return whether to decode all the frames and store them in memory
 */
public boolean getDecodeAllFrames(){
  return mDecodeAllFrames;
}

/** 
 * Returns the  {@link ByteBufAllocator} for this {@link RequestContext}. Any buffers created by this {@link ByteBufAllocator} must be<a href="https://netty.io/wiki/reference-counted-objects.html">reference-counted</a>. If you don't know what this means, you should probably use  {@code byte[]} or {@link ByteBuffer} directly insteadof calling this method.
 */
default ByteBufAllocator alloc(){
  throw new UnsupportedOperationException("No ByteBufAllocator available for this RequestContext.");
}

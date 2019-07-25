/** 
 * Deserialize (retrieve) this bitmap. See format specification at https://github.com/RoaringBitmap/RoaringFormatSpec The current bitmap is overwritten. It is not necessary that limit() on the input ByteBuffer indicates the end of the serialized data. After loading this RoaringBitmap, you can advance to the rest of the data (if there is more) by setting bbf.position(bbf.position() + bitmap.serializedSizeInBytes()); Note that the input ByteBuffer is effectively copied (with the slice operation) so you should expect the provided ByteBuffer position/mark/limit/order to remain unchanged.
 * @param bbf the byte buffer (can be mapped, direct, array backed etc.
 */
public void deserialize(ByteBuffer bbf){
  this.clear();
  ByteBuffer buffer=bbf.order() == LITTLE_ENDIAN ? bbf : bbf.slice().order(LITTLE_ENDIAN);
  final int cookie=buffer.getInt();
  if ((cookie & 0xFFFF) != SERIAL_COOKIE && cookie != SERIAL_COOKIE_NO_RUNCONTAINER) {
    throw new RuntimeException("I failed to find one of the right cookies. " + cookie);
  }
  boolean hasRunContainers=(cookie & 0xFFFF) == SERIAL_COOKIE;
  this.size=hasRunContainers ? (cookie >>> 16) + 1 : buffer.getInt();
  if (this.size > (1 << 16)) {
    throw new InvalidRoaringFormat("Size too large");
  }
  if ((this.keys == null) || (this.keys.length < this.size)) {
    this.keys=new short[this.size];
    this.values=new MappeableContainer[this.size];
  }
  byte[] bitmapOfRunContainers=null;
  boolean hasrun=(cookie & 0xFFFF) == SERIAL_COOKIE;
  if (hasrun) {
    bitmapOfRunContainers=new byte[(size + 7) / 8];
    buffer.get(bitmapOfRunContainers);
  }
  final short[] keys=new short[this.size];
  final int[] cardinalities=new int[this.size];
  final boolean[] isBitmap=new boolean[this.size];
  for (int k=0; k < this.size; ++k) {
    keys[k]=buffer.getShort();
    cardinalities[k]=1 + (0xFFFF & buffer.getShort());
    isBitmap[k]=cardinalities[k] > MappeableArrayContainer.DEFAULT_MAX_SIZE;
    if (bitmapOfRunContainers != null && (bitmapOfRunContainers[k / 8] & (1 << (k & 7))) != 0) {
      isBitmap[k]=false;
    }
  }
  if ((!hasrun) || (this.size >= NO_OFFSET_THRESHOLD)) {
    buffer.position(buffer.position() + this.size * 4);
  }
  for (int k=0; k < this.size; ++k) {
    MappeableContainer container;
    if (isBitmap[k]) {
      long[] array=new long[MappeableBitmapContainer.MAX_CAPACITY / 64];
      buffer.asLongBuffer().get(array);
      container=new MappeableBitmapContainer(cardinalities[k],LongBuffer.wrap(array));
      buffer.position(buffer.position() + 1024 * 8);
    }
 else     if (bitmapOfRunContainers != null && ((bitmapOfRunContainers[k / 8] & (1 << (k & 7))) != 0)) {
      int nbrruns=toIntUnsigned(buffer.getShort());
      int length=2 * nbrruns;
      short[] array=new short[length];
      buffer.asShortBuffer().get(array);
      container=new MappeableRunContainer(ShortBuffer.wrap(array),nbrruns);
      buffer.position(buffer.position() + length * 2);
    }
 else {
      int cardinality=cardinalities[k];
      short[] array=new short[cardinality];
      buffer.asShortBuffer().get(array);
      container=new MappeableArrayContainer(ShortBuffer.wrap(array),cardinality);
      buffer.position(buffer.position() + cardinality * 2);
    }
    this.keys[k]=keys[k];
    this.values[k]=container;
  }
}

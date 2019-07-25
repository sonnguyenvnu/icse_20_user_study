/** 
 * Deserialize.
 * @param in the DataInput stream
 * @throws IOException Signals that an I/O exception has occurred.
 */
public void deserialize(DataInput in) throws IOException {
  this.clear();
  final int cookie=Integer.reverseBytes(in.readInt());
  if ((cookie & 0xFFFF) != SERIAL_COOKIE && cookie != SERIAL_COOKIE_NO_RUNCONTAINER) {
    throw new InvalidRoaringFormat("I failed to find a valid cookie.");
  }
  this.size=((cookie & 0xFFFF) == SERIAL_COOKIE) ? (cookie >>> 16) + 1 : Integer.reverseBytes(in.readInt());
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
    in.readFully(bitmapOfRunContainers);
  }
  final short keys[]=new short[this.size];
  final int cardinalities[]=new int[this.size];
  final boolean isBitmap[]=new boolean[this.size];
  for (int k=0; k < this.size; ++k) {
    keys[k]=Short.reverseBytes(in.readShort());
    cardinalities[k]=1 + (0xFFFF & Short.reverseBytes(in.readShort()));
    isBitmap[k]=cardinalities[k] > MappeableArrayContainer.DEFAULT_MAX_SIZE;
    if (bitmapOfRunContainers != null && (bitmapOfRunContainers[k / 8] & (1 << (k % 8))) != 0) {
      isBitmap[k]=false;
    }
  }
  if ((!hasrun) || (this.size >= NO_OFFSET_THRESHOLD)) {
    in.skipBytes(this.size * 4);
  }
  for (int k=0; k < this.size; ++k) {
    MappeableContainer val;
    if (isBitmap[k]) {
      final LongBuffer bitmapArray=LongBuffer.allocate(MappeableBitmapContainer.MAX_CAPACITY / 64);
      for (int l=0; l < bitmapArray.limit(); ++l) {
        bitmapArray.put(l,Long.reverseBytes(in.readLong()));
      }
      val=new MappeableBitmapContainer(bitmapArray,cardinalities[k]);
    }
 else     if (bitmapOfRunContainers != null && ((bitmapOfRunContainers[k / 8] & (1 << (k % 8))) != 0)) {
      int nbrruns=toIntUnsigned(Short.reverseBytes(in.readShort()));
      final ShortBuffer shortArray=ShortBuffer.allocate(2 * nbrruns);
      for (int l=0; l < shortArray.limit(); ++l) {
        shortArray.put(l,Short.reverseBytes(in.readShort()));
      }
      val=new MappeableRunContainer(shortArray,nbrruns);
    }
 else {
      final ShortBuffer shortArray=ShortBuffer.allocate(cardinalities[k]);
      for (int l=0; l < shortArray.limit(); ++l) {
        shortArray.put(l,Short.reverseBytes(in.readShort()));
      }
      val=new MappeableArrayContainer(shortArray,cardinalities[k]);
    }
    this.keys[k]=keys[k];
    this.values[k]=val;
  }
}

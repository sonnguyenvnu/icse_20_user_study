/** 
 * Deserialize.
 * @param in the DataInput stream
 * @param buffer The buffer gets overwritten with data during deserialization. You can pass a NULLreference as a buffer. A buffer containing at least 8192 bytes might be ideal for performance. It is recommended to reuse the buffer between calls to deserialize (in a single-threaded context) for best performance.
 * @throws IOException Signals that an I/O exception has occurred.
 * @throws InvalidRoaringFormat if a Roaring Bitmap cookie is missing.
 */
public void deserialize(DataInput in,byte[] buffer) throws IOException {
  if (buffer != null && buffer.length == 0) {
    buffer=null;
  }
 else   if (buffer != null && buffer.length % 8 != 0) {
    throw new IllegalArgumentException("We need a buffer with a length multiple of 8. was length=" + buffer.length);
  }
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
    this.values=new Container[this.size];
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
    isBitmap[k]=cardinalities[k] > ArrayContainer.DEFAULT_MAX_SIZE;
    if (bitmapOfRunContainers != null && (bitmapOfRunContainers[k / 8] & (1 << (k % 8))) != 0) {
      isBitmap[k]=false;
    }
  }
  if ((!hasrun) || (this.size >= NO_OFFSET_THRESHOLD)) {
    in.skipBytes(this.size * 4);
  }
  for (int k=0; k < this.size; ++k) {
    Container val;
    if (isBitmap[k]) {
      final long[] bitmapArray=new long[BitmapContainer.MAX_CAPACITY / 64];
      if (buffer == null) {
        buffer=new byte[(BitmapContainer.MAX_CAPACITY / 64) * 8];
      }
      if (buffer.length < (BitmapContainer.MAX_CAPACITY / 64) * 8) {
        for (int iBlock=0; iBlock <= bitmapArray.length / buffer.length / 8; iBlock++) {
          int start=buffer.length * iBlock;
          int end=Math.min(buffer.length * (iBlock + 1) - 1,8 * bitmapArray.length);
          in.readFully(buffer,0,end - start);
          ByteBuffer asByteBuffer=ByteBuffer.wrap(buffer);
          asByteBuffer.order(LITTLE_ENDIAN);
          LongBuffer asLongBuffer=asByteBuffer.asLongBuffer();
          asLongBuffer.rewind();
          asLongBuffer.get(bitmapArray,start,(end - start) / 8);
        }
      }
 else {
        in.readFully(buffer,0,bitmapArray.length * 8);
        ByteBuffer asByteBuffer=ByteBuffer.wrap(buffer);
        asByteBuffer.order(LITTLE_ENDIAN);
        LongBuffer asLongBuffer=asByteBuffer.asLongBuffer();
        asLongBuffer.rewind();
        asLongBuffer.get(bitmapArray);
      }
      val=new BitmapContainer(bitmapArray,cardinalities[k]);
    }
 else     if (bitmapOfRunContainers != null && ((bitmapOfRunContainers[k / 8] & (1 << (k % 8))) != 0)) {
      int nbrruns=Util.toIntUnsigned(Short.reverseBytes(in.readShort()));
      final short lengthsAndValues[]=new short[2 * nbrruns];
      if (buffer == null && lengthsAndValues.length > (BitmapContainer.MAX_CAPACITY / 64) * 8) {
        buffer=new byte[(BitmapContainer.MAX_CAPACITY / 64) * 8];
      }
      if (buffer == null) {
        for (int j=0; j < lengthsAndValues.length; ++j) {
          lengthsAndValues[j]=Short.reverseBytes(in.readShort());
        }
      }
 else {
        for (int iBlock=0; iBlock <= lengthsAndValues.length / buffer.length / 2; iBlock++) {
          int start=buffer.length * iBlock;
          int end=Math.min(buffer.length * (iBlock + 1) - 1,2 * lengthsAndValues.length);
          in.readFully(buffer,0,end - start);
          ByteBuffer asByteBuffer=ByteBuffer.wrap(buffer);
          asByteBuffer.order(LITTLE_ENDIAN);
          ShortBuffer asShortBuffer=asByteBuffer.asShortBuffer();
          asShortBuffer.rewind();
          asShortBuffer.get(lengthsAndValues,start,(end - start) / 2);
        }
      }
      val=new RunContainer(lengthsAndValues,nbrruns);
    }
 else {
      final short[] shortArray=new short[cardinalities[k]];
      if (buffer == null && shortArray.length > (BitmapContainer.MAX_CAPACITY / 64) * 8) {
        buffer=new byte[(BitmapContainer.MAX_CAPACITY / 64) * 8];
      }
      if (buffer == null) {
        for (int j=0; j < shortArray.length; ++j) {
          shortArray[j]=Short.reverseBytes(in.readShort());
        }
      }
 else {
        for (int iBlock=0; iBlock <= shortArray.length / buffer.length / 2; iBlock++) {
          int start=buffer.length * iBlock;
          int end=Math.min(buffer.length * (iBlock + 1) - 1,2 * shortArray.length);
          in.readFully(buffer,0,end - start);
          ByteBuffer asByteBuffer=ByteBuffer.wrap(buffer);
          asByteBuffer.order(LITTLE_ENDIAN);
          ShortBuffer asShortBuffer=asByteBuffer.asShortBuffer();
          asShortBuffer.rewind();
          asShortBuffer.get(shortArray,start,(end - start) / 2);
        }
      }
      val=new ArrayContainer(shortArray);
    }
    this.keys[k]=keys[k];
    this.values[k]=val;
  }
}

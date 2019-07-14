/** 
 * Writes Image data into a  {@code ByteBuffer}.
 */
private synchronized ByteBuffer convertBitmapToByteBuffer(ByteBuffer buffer,int width,int height){
  int bytesPerChannel=mUseQuantizedModel ? QUANT_NUM_OF_BYTES_PER_CHANNEL : FLOAT_NUM_OF_BYTES_PER_CHANNEL;
  ByteBuffer imgData=ByteBuffer.allocateDirect(bytesPerChannel * DIM_BATCH_SIZE * DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y * DIM_PIXEL_SIZE);
  imgData.order(ByteOrder.nativeOrder());
  Bitmap bitmap=createResizedBitmap(buffer,width,height);
  imgData.rewind();
  bitmap.getPixels(intValues,0,bitmap.getWidth(),0,0,bitmap.getWidth(),bitmap.getHeight());
  int pixel=0;
  long startTime=SystemClock.uptimeMillis();
  for (int i=0; i < DIM_IMG_SIZE_X; ++i) {
    for (int j=0; j < DIM_IMG_SIZE_Y; ++j) {
      final int val=intValues[pixel++];
      if (mUseQuantizedModel) {
        imgData.put((byte)((val >> 16) & 0xFF));
        imgData.put((byte)((val >> 8) & 0xFF));
        imgData.put((byte)(val & 0xFF));
      }
 else {
        imgData.putFloat(((val >> 16) & 0xFF) / 255.0f);
        imgData.putFloat(((val >> 8) & 0xFF) / 255.0f);
        imgData.putFloat((val & 0xFF) / 255.0f);
      }
    }
  }
  long endTime=SystemClock.uptimeMillis();
  Log.d(TAG,"Timecost to put values into ByteBuffer: " + (endTime - startTime));
  return imgData;
}

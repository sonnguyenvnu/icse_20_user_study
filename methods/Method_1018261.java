public NsfwBean run(Bitmap bitmap){
  Bitmap bitmap_256=Bitmap.createScaledBitmap(bitmap,256,256,true);
  convertBitmapToByteBuffer(bitmap_256);
  long startTime=SystemClock.uptimeMillis();
  float[][] outArray=new float[1][2];
  tflite.run(imgData,outArray);
  long endTime=SystemClock.uptimeMillis();
  Log.d(TAG,"SFW score :" + outArray[0][0] + ",NSFW score :" + outArray[0][1]);
  Log.d(TAG,"Timecost to run model inference: " + (endTime - startTime) + "ms");
  return new NsfwBean(outArray[0][0],outArray[0][1]);
}

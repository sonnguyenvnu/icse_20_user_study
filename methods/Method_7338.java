public String getString(){
  return String.format(Locale.US,"-1_%d_%d_%d_%d_%d_%d_%d_%d_%d_%s",startTime,endTime,rotationValue,originalWidth,originalHeight,bitrate,resultWidth,resultHeight,framerate,originalPath);
}

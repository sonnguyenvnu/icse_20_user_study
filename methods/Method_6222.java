long calculateDuration(MP3Input data,long totalLength,StopReadCondition stopCondition) throws IOException, MP3Exception {
  MP3Frame frame=readFirstFrame(data,stopCondition);
  if (frame != null) {
    int numberOfFrames=frame.getNumberOfFrames();
    if (numberOfFrames > 0) {
      return frame.getHeader().getTotalDuration(numberOfFrames * frame.getSize());
    }
 else {
      numberOfFrames=1;
      long firstFramePosition=data.getPosition() - frame.getSize();
      long frameSizeSum=frame.getSize();
      int firstFrameBitrate=frame.getHeader().getBitrate();
      long bitrateSum=firstFrameBitrate;
      boolean vbr=false;
      int cbrThreshold=10000 / frame.getHeader().getDuration();
      while (true) {
        if (numberOfFrames == cbrThreshold && !vbr && totalLength > 0) {
          return frame.getHeader().getTotalDuration(totalLength - firstFramePosition);
        }
        if ((frame=readNextFrame(data,stopCondition,frame)) == null) {
          break;
        }
        int bitrate=frame.getHeader().getBitrate();
        if (bitrate != firstFrameBitrate) {
          vbr=true;
        }
        bitrateSum+=bitrate;
        frameSizeSum+=frame.getSize();
        numberOfFrames++;
      }
      return 1000L * frameSizeSum * numberOfFrames * 8 / bitrateSum;
    }
  }
 else {
    throw new MP3Exception("No audio frame");
  }
}

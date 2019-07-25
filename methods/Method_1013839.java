public void encode(final int texture){
  final long time=System.nanoTime();
  final long timeDiff;
  if (this.checkPaused()) {
    timeDiff=0;
  }
 else {
    timeDiff=time - this.timeLast;
  }
  if (this.timeLast >= 0) {
    this.timeTotal+=timeDiff;
  }
  this.timeLast=time;
  this.audioRecorder.updateTime(this.timeTotal);
  this.drawEncode(texture,null);
}

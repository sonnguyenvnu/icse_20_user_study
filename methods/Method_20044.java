public void setMachineLearningFrameProcessor(VisionImageProcessor processor){
synchronized (processorLock) {
    cleanScreen();
    if (frameProcessor != null) {
      frameProcessor.stop();
    }
    frameProcessor=processor;
  }
}

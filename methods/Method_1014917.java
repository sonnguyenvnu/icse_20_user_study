@Override public void run(){
  sample=new FloatSample[numSamples];
  synth=JSyn.createSynthesizer(AudioDeviceFactory.createAudioDeviceManager(true));
  rates=new double[numSamples];
  volume=new float[numSamples];
  samplePlayer=new VariableRateDataReader[numSamples];
  lineOut=new LineOut();
  this.synth.add(lineOut);
  int loopStartFrame=Integer.MAX_VALUE;
  int loopSize=Integer.MAX_VALUE;
  for (int i=0; i < numSamples; i++) {
    try {
      this.sample[i]=SampleLoader.loadFloatSample(ResourceManager.getInstance().getResource(soundMixSamples[i]));
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
    if (sample[i].getChannelsPerFrame() == 1) {
      synth.add(samplePlayer[i]=new VariableRateMonoReader());
      samplePlayer[i].output.connect(0,lineOut.input,0);
    }
 else     if (sample[i].getChannelsPerFrame() == 2) {
      synth.add(samplePlayer[i]=new VariableRateStereoReader());
      samplePlayer[i].output.connect(0,lineOut.input,0);
      samplePlayer[i].output.connect(1,lineOut.input,1);
    }
 else {
      throw new RuntimeException("Can only play mono or stereo samples.");
    }
    loopStartFrame=(int)(sample[i].getNumFrames() * 0.2);
    loopSize=(int)(sample[i].getNumFrames() * 0.7);
    samplePlayer[i].rate.set(rates[i]=sample[i].getFrameRate() / 2);
    this.synth.start();
    lineOut.start();
    samplePlayer[i].dataQueue.queue(sample[i],0,loopStartFrame);
    samplePlayer[i].amplitude.set(volume[i] * masterVolume);
    volume[i]=defaultVolume[i];
    int crossFadeSize=(int)(2000);
    QueueDataCommand command=samplePlayer[i].dataQueue.createQueueDataCommand(sample[i],loopStartFrame,loopSize);
    command.setNumLoops(-1);
    command.setSkipIfOthers(true);
    command.setCrossFadeIn(crossFadeSize);
    System.out.println("Queue: " + loopStartFrame + ", #" + loopSize + ", X=" + crossFadeSize);
    synth.queueCommand(command);
  }
  samplePlayer[3].amplitude.set(0);
  running=true;
}

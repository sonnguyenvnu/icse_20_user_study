public void init(int samplesPerSecond,int bins,int numChannels){
  this.windowSize=samplesPerSecond;
  this.bins=bins;
  this.numChannels=numChannels;
  this.windows=new double[numChannels][samplesPerSecond];
  this.currentFFTs=new double[numChannels][samplesPerSecond];
  this.meanFFTs=new double[numChannels][samplesPerSecond];
  this.meanFFTValue=new double[numChannels];
  this.varFFTValue=new double[numChannels];
  this.currentFFTPhases=new double[numChannels][samplesPerSecond];
  this.currentFFTValue=new double[numChannels];
  this.currentFFTBins=new double[bins][numChannels];
  this.meanFFTValueCount=new int[bins][numChannels];
  this.meanFFTBins=new double[bins][numChannels];
  this.varFFTBins=new double[bins][numChannels];
  this.relativeFFTBins=new double[bins][numChannels];
  this.baselineFFTValues=new double[bins][numChannels];
  this.shortMeanFFTBins=new double[bins][numChannels];
  this.shortVarFFTBins=new double[bins][numChannels];
  this.rewardFFTBins=new double[bins][numChannels];
  this.currentFFTValue=new double[numChannels];
  this.maxFFTValue=new double[numChannels];
  this.notBrainwaves=new boolean[numChannels];
  this.packagePenalty=new int[numChannels];
  for (int c=0; c < numChannels; c++) {
    this.maxFFTValue[c]=1d;
    this.currentFFTValue[c]=.01d;
    this.meanFFTValue[c]=.5d;
  }
}

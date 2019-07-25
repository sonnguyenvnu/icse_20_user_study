public void base(){
  if ((fftData.baselineFFTValues != null) && (fftData.meanFFTBins != null)) {
    for (int b=0; b < fftData.bins; b++)     fftData.baselineFFTValues[b]=fftData.meanFFTBins[b].clone();
  }
}

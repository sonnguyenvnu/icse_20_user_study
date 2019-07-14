private void updateSampleStreams(SampleStream[] streams){
  hlsSampleStreams.clear();
  for (  SampleStream stream : streams) {
    if (stream != null) {
      hlsSampleStreams.add((HlsSampleStream)stream);
    }
  }
}

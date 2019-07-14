private void buildDataflowJobSettings(final JobSettings result,final DataflowJobConfiguration config){
  result.setStreamingProcess(config.isStreamingProcess());
}

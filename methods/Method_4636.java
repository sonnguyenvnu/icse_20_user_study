private void outputPendingMetadataSamples(long sampleTimeUs){
  while (!pendingMetadataSampleInfos.isEmpty()) {
    MetadataSampleInfo sampleInfo=pendingMetadataSampleInfos.removeFirst();
    pendingMetadataSampleBytes-=sampleInfo.size;
    long metadataTimeUs=sampleTimeUs + sampleInfo.presentationTimeDeltaUs;
    if (timestampAdjuster != null) {
      metadataTimeUs=timestampAdjuster.adjustSampleTimestamp(metadataTimeUs);
    }
    for (    TrackOutput emsgTrackOutput : emsgTrackOutputs) {
      emsgTrackOutput.sampleMetadata(metadataTimeUs,C.BUFFER_FLAG_KEY_FRAME,sampleInfo.size,pendingMetadataSampleBytes,null);
    }
  }
}

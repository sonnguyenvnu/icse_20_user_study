private void updateFlacStreamInfo(ExtractorInput input,FlacStreamInfo streamInfo){
  this.streamInfo=streamInfo;
  outputSeekMap(input,streamInfo);
  outputFormat(streamInfo);
  outputBuffer=new ParsableByteArray(streamInfo.maxDecodedFrameSize());
  outputByteBuffer=ByteBuffer.wrap(outputBuffer.data);
  outputFrameHolder=new BinarySearchSeeker.OutputFrameHolder(outputByteBuffer);
}

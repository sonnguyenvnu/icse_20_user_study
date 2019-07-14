private void replaceDecoder(){
  releaseDecoder();
  decoder=decoderFactory.createDecoder(streamFormat);
}

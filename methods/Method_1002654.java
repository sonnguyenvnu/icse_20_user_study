@Override public HttpData finish(){
  if (decoder.finish()) {
    return fetchDecoderOutput();
  }
 else {
    return HttpData.EMPTY_DATA;
  }
}

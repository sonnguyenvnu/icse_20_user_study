public GeminiTrailingVolumeResponse Get30DayTrailingVolumeDescription() throws IOException {
  try {
    GeminiTrailingVolumeRequest request=new GeminiTrailingVolumeRequest(String.valueOf(exchange.getNonceFactory().createValue()));
    GeminiTrailingVolumeResponse trailingVolResp=gemini.TrailingVolume(apiKey,payloadCreator,signatureCreator,request);
    return trailingVolResp;
  }
 catch (  GeminiException e) {
    throw handleException(e);
  }
}

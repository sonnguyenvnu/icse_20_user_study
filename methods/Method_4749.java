/** 
 * If  {@link #FLAG_OVERRIDE_CAPTION_DESCRIPTORS} is set, returns a {@link SeiReader} for{@link #closedCaptionFormats}. If unset, parses the PMT descriptor information and returns a {@link SeiReader} for the declared formats, or {@link #closedCaptionFormats} if the descriptoris not present.
 * @param esInfo The {@link EsInfo} passed to {@link #createPayloadReader(int,EsInfo)}.
 * @return A {@link SeiReader} for closed caption tracks.
 */
private SeiReader buildSeiReader(EsInfo esInfo){
  return new SeiReader(getClosedCaptionFormats(esInfo));
}

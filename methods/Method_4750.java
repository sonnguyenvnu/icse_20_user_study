/** 
 * If  {@link #FLAG_OVERRIDE_CAPTION_DESCRIPTORS} is set, returns a {@link UserDataReader} for{@link #closedCaptionFormats}. If unset, parses the PMT descriptor information and returns a {@link UserDataReader} for the declared formats, or {@link #closedCaptionFormats} if thedescriptor is not present.
 * @param esInfo The {@link EsInfo} passed to {@link #createPayloadReader(int,EsInfo)}.
 * @return A {@link UserDataReader} for closed caption tracks.
 */
private UserDataReader buildUserDataReader(EsInfo esInfo){
  return new UserDataReader(getClosedCaptionFormats(esInfo));
}

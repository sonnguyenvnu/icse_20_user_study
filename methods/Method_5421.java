/** 
 * Returns a playlist identical to this one except for the start time, the discontinuity sequence and  {@code hasDiscontinuitySequence} values. The first two are set to the specified values,{@code hasDiscontinuitySequence} is set to true.
 * @param startTimeUs The start time for the returned playlist.
 * @param discontinuitySequence The discontinuity sequence for the returned playlist.
 * @return An identical playlist including the provided discontinuity and timing information.
 */
public HlsMediaPlaylist copyWith(long startTimeUs,int discontinuitySequence){
  return new HlsMediaPlaylist(playlistType,baseUri,tags,startOffsetUs,startTimeUs,true,discontinuitySequence,mediaSequence,version,targetDurationUs,hasIndependentSegments,hasEndTag,hasProgramDateTime,protectionSchemes,segments);
}

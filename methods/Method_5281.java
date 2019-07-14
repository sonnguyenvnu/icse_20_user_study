/** 
 * Attempts to merge this  {@link RangedUri} with another and an optional common base uri.<p>A merge is successful if both instances define the same  {@link Uri} after resolution withthe base uri, and if one starts the byte after the other ends, forming a contiguous region with no overlap. <p>If  {@code other} is null then the merge is considered unsuccessful, and null is returned.
 * @param other The {@link RangedUri} to merge.
 * @param baseUri The optional base Uri.
 * @return The merged {@link RangedUri} if the merge was successful. Null otherwise.
 */
public @Nullable RangedUri attemptMerge(@Nullable RangedUri other,String baseUri){
  final String resolvedUri=resolveUriString(baseUri);
  if (other == null || !resolvedUri.equals(other.resolveUriString(baseUri))) {
    return null;
  }
 else   if (length != C.LENGTH_UNSET && start + length == other.start) {
    return new RangedUri(resolvedUri,start,other.length == C.LENGTH_UNSET ? C.LENGTH_UNSET : length + other.length);
  }
 else   if (other.length != C.LENGTH_UNSET && other.start + other.length == start) {
    return new RangedUri(resolvedUri,other.start,length == C.LENGTH_UNSET ? C.LENGTH_UNSET : other.length + length);
  }
 else {
    return null;
  }
}

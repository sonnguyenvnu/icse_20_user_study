/** 
 * Validates if this  {@link DLNAOrgOperations} instance can be used togetherwith a given  {@link DLNAOrgFlags} instance without breaking DLNA rules.
 * @param flags the {@link DLNAOrgFlags} instance to validate against.
 * @return {@code true} if validation succeeded or {@code flags} was{@code null},  {@code false} otherwise.
 */
public boolean validate(DLNAOrgFlags flags){
  if (flags != null && state > 0) {
    if (flags.isS0Increasing() || flags.isLimitedOperationsTimeBasedSeek() || flags.isLimitedOperationsByteBasedSeek()) {
      return false;
    }
  }
  return true;
}

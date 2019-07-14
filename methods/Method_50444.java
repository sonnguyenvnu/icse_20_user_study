/** 
 * add participant.
 * @param hmilyParticipant {@linkplain HmilyParticipant}
 */
public void enlistParticipant(final HmilyParticipant hmilyParticipant){
  if (Objects.isNull(hmilyParticipant)) {
    return;
  }
  Optional.ofNullable(getCurrentTransaction()).ifPresent(c -> {
    c.registerParticipant(hmilyParticipant);
    updateParticipant(c);
  }
);
}

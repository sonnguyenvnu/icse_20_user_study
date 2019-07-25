private Participant participantsget(String code){
  for (  Participant p : participantsList) {
    if (p.getCode().equals(code)) {
      return p;
    }
  }
  return null;
}

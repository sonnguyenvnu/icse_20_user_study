private List<HmilyParticipant> filterPoint(final HmilyTransaction currentTransaction){
  final List<HmilyParticipant> hmilyParticipants=currentTransaction.getHmilyParticipants();
  if (CollectionUtils.isNotEmpty(hmilyParticipants)) {
    if (currentTransaction.getStatus() == HmilyActionEnum.TRYING.getCode() && currentTransaction.getRole() == HmilyRoleEnum.START.getCode()) {
      return hmilyParticipants.stream().limit(hmilyParticipants.size()).filter(Objects::nonNull).collect(Collectors.toList());
    }
  }
  return hmilyParticipants;
}

public List<String> getAllUserId(){
  return getAllPerson().stream().map(PersonEntity::getUserId).collect(Collectors.toList());
}

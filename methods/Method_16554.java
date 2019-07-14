public List<String> getAllPersonId(){
  return getAllPerson().stream().map(PersonEntity::getId).collect(Collectors.toList());
}

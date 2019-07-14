@Override public List<UserToken> getByUserId(String userId){
  if (userId == null) {
    return new ArrayList<>();
  }
  Set<String> tokens=getUserToken(userId);
  if (tokens.isEmpty()) {
    userStorage.remove(userId);
    return new ArrayList<>();
  }
  return tokens.stream().map(tokenStorage::get).filter(Objects::nonNull).collect(Collectors.toList());
}

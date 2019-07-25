private List<BrokerGroup> select(final List<BrokerGroup> groups){
  if (groups == null || groups.size() == 0) {
    return null;
  }
  if (groups.size() <= DEFAULT_MIN_NUM) {
    return groups;
  }
  final ThreadLocalRandom random=ThreadLocalRandom.current();
  final Set<BrokerGroup> resultSet=new HashSet<>(DEFAULT_MIN_NUM);
  while (resultSet.size() <= DEFAULT_MIN_NUM) {
    final int randomIndex=random.nextInt(groups.size());
    resultSet.add(groups.get(randomIndex));
  }
  return new ArrayList<>(resultSet);
}

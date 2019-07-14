private boolean isTarget(final String relativePath){
  return !isNullOrEmpty(relativePath) && and(predicates).apply(relativePath);
}

private void checkForParameter(final Set<String> paramNames,final String nameToSearch){
  final Set<String> paramsContained=new HashSet<>();
  for (  final String param : paramNames) {
    if (containsAny(nameToSearch,formatNameVariations(param))) {
      paramsContained.add(param);
    }
  }
  paramNames.removeAll(paramsContained);
}

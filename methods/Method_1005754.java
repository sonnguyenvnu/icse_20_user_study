static Set<String> resources(Set<Target> targets){
  return targets.stream().filter(targetDep -> targetDep instanceof AndroidTarget).map(targetDep -> resRule((AndroidTarget)targetDep)).collect(Collectors.toSet());
}

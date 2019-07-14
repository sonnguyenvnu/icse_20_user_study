private static DistributionVersion findVersion(DistributionVersion[] versions,String target){
  for (  DistributionVersion dv : versions) {
    if (dv.getValue().equals(target)) {
      return dv;
    }
  }
  return null;
}

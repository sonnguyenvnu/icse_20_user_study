public boolean check(CompatibilityLevel level){
  if (!_checked) {
    runCheck();
  }
  return _infoMap.isCompatible(level);
}

private int getNumberOfCores(){
  if (Build.VERSION.SDK_INT >= 17) {
    return Runtime.getRuntime().availableProcessors();
  }
 else {
    return getNumCoresOldPhones();
  }
}

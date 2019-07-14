private void checkKeyPresent(String key){
  checkArgument(containsKey(key),key + "' not present");
}

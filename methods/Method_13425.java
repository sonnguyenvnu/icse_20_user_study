private void excludeSelf(Set<String> subscribedServices){
  subscribedServices.remove(currentApplicationName);
}

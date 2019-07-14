boolean contains(@NonNull Controller controller){
  for (  RouterTransaction transaction : backstack) {
    if (controller == transaction.controller) {
      return true;
    }
  }
  return false;
}

public boolean match(KeyEvent event){
  if (Objects.nonNull(combination)) {
    return combination.match(event);
  }
  return Arrays.stream(keyCodes).filter(k -> event.getCode() == k).findAny().isPresent();
}

private static boolean isFirstLetterCapital(@Nullable String aText){
  return Optional.ofNullable(aText).filter(text -> text.length() > 0).map(text -> text.charAt(0)).filter(Character::isUpperCase).isPresent();
}

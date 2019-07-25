@NotNull public static String lcfirst(@NotNull String input){
  if (input.length() < 1) {
    return input;
  }
  return Character.toLowerCase(input.charAt(0)) + (input.length() > 1 ? input.substring(1) : "");
}

static boolean startsWithIs(String original){
  return original.startsWith("is") && original.length() > 2 && Character.isUpperCase(original.charAt(2));
}

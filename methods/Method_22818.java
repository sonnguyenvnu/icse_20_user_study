protected int getStringMapKey(String s){
  return (Character.toUpperCase(s.charAt(0)) + Character.toUpperCase(s.charAt(s.length() - 1))) % MAP_LENGTH;
}

public static boolean isValid(byte lifecycle){
  return lifecycle >= New && lifecycle <= Removed;
}

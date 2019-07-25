public static String ucfirst(String chaine){
  if (chaine.length() < 1) {
    return chaine;
  }
  return chaine.substring(0,1).toUpperCase() + chaine.substring(1);
}

public static boolean match(URIPattern[] patterns,URI uri){
  URI normalized=uri.normalize();
  for (  URIPattern pattern : patterns) {
    if (pattern.matchNormalized(normalized)) {
      return true;
    }
  }
  return false;
}

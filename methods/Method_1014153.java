private URI map(String pathWithScheme){
  java.net.URI javaNetUri=toURI(pathWithScheme,clientLocation);
  logger.trace("Going to map path {}",javaNetUri);
  URI ret=URI.createURI(toPathAsInXtext212(javaNetUri));
  logger.trace("Mapped path {} to {}",pathWithScheme,ret);
  return ret;
}

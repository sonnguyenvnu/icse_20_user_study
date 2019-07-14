public Optional<String> encodingPart(){
  for (int i=1; i < parts.length; i++) {
    if (parts[i].matches("\\s*charset\\s*=.*")) {
      return Optional.of(parts[i].split("=")[1].replace("\"",""));
    }
  }
  return Optional.absent();
}

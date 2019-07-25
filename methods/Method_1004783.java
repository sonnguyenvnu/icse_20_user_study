@Override public String convert(DeserializationFailure entry){
  return String.format("Dropping malformed scroll search hit due to error [%s]:%nEntry Contents:%n%s",entry.getException().getMessage(),((FastByteArrayInputStream)entry.getHitContents()).bytes().toString());
}

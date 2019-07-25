@Override public String convert(SerializationFailure entry){
  return String.format("Dropping malformed output record due to error while serializing [%s]:%n" + "Entry Contents:%n" + "%s",entry.getException().getMessage(),stringify(entry.getRecord()));
}

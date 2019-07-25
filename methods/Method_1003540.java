public PropertyEntry pop(){
  if (fullName.length > 1) {
    String[] newFullName=Arrays.copyOfRange(fullName,1,fullName.length);
    return new PropertyEntry(newFullName,readAccessor,writeAccessor,presenceChecker,type,builderType);
  }
 else {
    return null;
  }
}

public static void verifyOpen(boolean isOpen,String resourceName,String... resourceIdentifiers){
  Preconditions.checkArgument(StringUtils.isNotBlank(resourceName));
  if (!isOpen) {
    StringBuilder msg=new StringBuilder();
    msg.append(resourceName).append(" ");
    if (resourceIdentifiers != null && resourceIdentifiers.length > 0) {
      msg.append("[");
      for (int i=0; i < resourceIdentifiers.length; i++) {
        if (i > 0)         msg.append(",");
        msg.append(resourceIdentifiers[i]);
      }
      msg.append("] ");
    }
    msg.append("has been closed");
    throw new ResourceUnavailableException(msg.toString());
  }
}

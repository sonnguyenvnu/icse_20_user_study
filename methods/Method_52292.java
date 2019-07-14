public boolean allowsAllAssignments(){
  return getProperty(ALLOW_IF_DESCRIPTOR) && getProperty(ALLOW_FOR_DESCRIPTOR) && getProperty(ALLOW_WHILE_DESCRIPTOR) && getProperty(ALLOW_INCREMENT_DECREMENT_DESCRIPTOR);
}

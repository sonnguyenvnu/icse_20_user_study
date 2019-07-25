@Override public void run(){
  List<String> unassignType=unassignActionConfig.unassignType;
  if (unassignType == null || unassignType.isEmpty()) {
    throw new IllegalArgumentException("Must specify a single type to unassign.");
  }
  if (unassignActionConfig.name == null || !validName(unassignActionConfig.name) || unassignActionConfig.group == null || !validName(unassignActionConfig.group)) {
    throw new IllegalArgumentException(format("Invalid name, must match %s",VALID_NAME_PATTERN));
  }
  Group group;
  try {
    group=keywhizClient.getGroupByName(unassignActionConfig.group);
    if (group == null) {
      throw new AssertionError("Group doesn't exist.");
    }
  }
 catch (  IOException e) {
    throw Throwables.propagate(e);
  }
  String firstType=unassignType.get(0).toLowerCase().trim();
switch (firstType) {
case "client":
    try {
      Client client=keywhizClient.getClientByName(unassignActionConfig.name);
      if (!keywhizClient.groupDetailsForId(group.getId()).getClients().contains(client)) {
        throw new AssertionError(format("Client '%s' not assigned to group '%s'.",unassignActionConfig.name,group));
      }
      logger.info("Evicting client '{}' from group '{}'.",client.getName(),group.getName());
      keywhizClient.evictClientFromGroupByIds(client.getId(),group.getId());
    }
 catch (    NotFoundException e) {
      throw new AssertionError("Client or group doesn't exist.");
    }
catch (    IOException e) {
      throw Throwables.propagate(e);
    }
  break;
case "secret":
try {
  long groupId=group.getId();
  SanitizedSecret sanitizedSecret=keywhizClient.getSanitizedSecretByName(unassignActionConfig.name);
  if (!keywhizClient.groupDetailsForId(groupId).getSecrets().contains(sanitizedSecret)) {
    throw new AssertionError(format("Secret '%s' not assigned to group '%s'",unassignActionConfig.name,group));
  }
  logger.info("Revoke group '{}' access to secret '{}'.",group.getName(),SanitizedSecret.displayName(sanitizedSecret));
  keywhizClient.revokeSecretFromGroupByIds(sanitizedSecret.id(),groupId);
}
 catch (NotFoundException e) {
  throw new AssertionError("Secret or group doesn't exist.");
}
catch (IOException e) {
  throw Throwables.propagate(e);
}
break;
default :
throw new IllegalArgumentException("Invalid unassign type specified: " + firstType);
}
}

/** 
 * Unregisters the  {@link EndpointGroup} with the specified case-insensitive {@code groupName}. Note that this is potentially a dangerous operation; make sure the  {@code groupName} of the unregistered{@link EndpointGroup} is not in use by any clients.
 * @return {@code true} if the {@link EndpointGroup} with the specified {@code groupName} has been removed.{@code false} if there's no such {@link EndpointGroup} in the registry.
 */
public static boolean unregister(String groupName){
  groupName=normalizeGroupName(groupName);
  return serverGroups.remove(groupName) != null;
}

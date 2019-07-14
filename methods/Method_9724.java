/** 
 * Checks whether the specified grant permissions contains the specified requisite permissions.
 * @param requisitePermissions the specified requisite permissions
 * @param grantPermissions     the specified grant permissions
 * @return {@code true} if the specified grant permissions contains the specified requisite permissions, returns{@code false} otherwise
 */
public static boolean hasPermission(final Set<String> requisitePermissions,final Set<String> grantPermissions){
  for (  final String requisitePermission : requisitePermissions) {
    if (!grantPermissions.contains(requisitePermission)) {
      return false;
    }
  }
  return true;
}

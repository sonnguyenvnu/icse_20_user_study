/** 
 * Forbid all mentioned visibilities.
 * @param visibilities The visibilities to forbid
 */
public void forbid(Visibility... visibilities){
  visMask.removeAll(Arrays.asList(visibilities));
}

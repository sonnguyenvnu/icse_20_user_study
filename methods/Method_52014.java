/** 
 * Restricts the visibilities covered by the mask to the parameters.
 * @param visibilities The visibilities to cover
 */
public void restrictVisibilitiesTo(JavaSignature.Visibility... visibilities){
  visMask.clear();
  visMask.addAll(Arrays.asList(visibilities));
}

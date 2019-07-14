/** 
 * Copies various properties from one drawable to the other.
 * @param to drawable to copy properties to
 * @param from drawable to copy properties from
 */
public static void copyProperties(@Nullable Drawable to,@Nullable Drawable from){
  if (from == null || to == null || to == from) {
    return;
  }
  to.setBounds(from.getBounds());
  to.setChangingConfigurations(from.getChangingConfigurations());
  to.setLevel(from.getLevel());
  to.setVisible(from.isVisible(),false);
  to.setState(from.getState());
}

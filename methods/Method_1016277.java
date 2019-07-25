/** 
 * Enables specified components.
 * @param disabled disabled components list
 */
public static void enable(final List<Component> disabled){
  for (  final Component component : disabled) {
    component.setEnabled(true);
  }
}

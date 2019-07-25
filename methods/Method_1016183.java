/** 
 * Installs component move adapter to the specified component.
 * @param component component that will act as gripper
 * @param toDrag    component to be moved by the gripper component
 */
public static void install(final Component component,final Component toDrag){
  final ComponentMoveAdapter wma=new ComponentMoveAdapter(toDrag);
  component.addMouseListener(wma);
  component.addMouseMotionListener(wma);
}

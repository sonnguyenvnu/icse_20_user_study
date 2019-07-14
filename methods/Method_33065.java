/** 
 * set actions of the dialog (Accept, Cancel,...)
 * @param actions
 */
public void setActions(Node... actions){
  this.actions.getChildren().setAll(actions);
}

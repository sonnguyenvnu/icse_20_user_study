/** 
 * * Setters / Getters * 
 */
public void setControl(Node control){
  if (control != null) {
    this.control=control;
    this.badge=new Group();
    this.getChildren().add(control);
    this.getChildren().add(badge);
    if (control instanceof Region) {
      ((Region)control).widthProperty().addListener((o,oldVal,newVal) -> refreshBadge());
      ((Region)control).heightProperty().addListener((o,oldVal,newVal) -> refreshBadge());
    }
    text.addListener((o,oldVal,newVal) -> refreshBadge());
  }
}

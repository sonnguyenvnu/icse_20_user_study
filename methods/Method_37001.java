/** 
 * unbind the data, make the view re-usable
 */
public void unbind(){
  if (itemView == null || controller == null) {
    return;
  }
  if (data != null) {
    this.controller.unmountView(data,itemView);
  }
}

/** 
 * unbind the data, make the view re-usable
 */
public void unbind(){
  if (data != null) {
    this.controller.unmountView(data,itemView);
  }
}

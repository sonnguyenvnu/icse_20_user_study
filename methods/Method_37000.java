/** 
 * Bind data to inner view
 * @param data
 */
public void bind(C data){
  if (itemView == null || controller == null) {
    return;
  }
  this.controller.mountView(data,itemView);
  this.data=data;
}

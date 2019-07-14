/** 
 * Bind data to inner view
 * @param data
 */
public void bind(C data){
  this.controller.mountView(data,itemView);
  this.data=data;
}

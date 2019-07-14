/** 
 * set compiled binary data of virtual views.* @param data
 */
public int setVirtualViewTemplate(byte[] data){
  ViewManager viewManager=getService(ViewManager.class);
  return viewManager.loadBinBufferSync(data);
}

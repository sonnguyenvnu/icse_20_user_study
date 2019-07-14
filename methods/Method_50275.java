/** 
 * ????
 */
private void setPostMediaBean(MediaBean mediaBean){
  ImageCropBean bean=new ImageCropBean();
  bean.copyMediaBean(mediaBean);
  RxBus.getDefault().post(new ImageRadioResultEvent(bean));
}

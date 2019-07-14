/** 
 * ?? Video  ??  ????
 * @param mediaBean
 */
private void videoRadioNext(MediaBean mediaBean){
  if (!mConfiguration.isVideoPreview()) {
    setPostMediaBean(mediaBean);
    getActivity().finish();
    return;
  }
  try {
    Intent openVideo=new Intent(Intent.ACTION_VIEW);
    openVideo.setDataAndType(Uri.parse(mediaBean.getOriginalPath()),"video/*");
    startActivity(openVideo);
  }
 catch (  Exception e) {
    Toast.makeText(getContext(),"???????",Toast.LENGTH_SHORT).show();
  }
}

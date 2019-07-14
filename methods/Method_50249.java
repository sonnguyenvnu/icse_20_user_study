@Override public Job create(){
  Job.Params params=new Job.Params(mediaBean.getOriginalPath(),mediaBean);
  return new ImageThmbnailJob(context,params);
}

/** 
 * ????
 * @param pictureList
 */
private void setPicture(List<String> pictureList){
  List<Entry<String,String>> keyValueList=new ArrayList<Entry<String,String>>();
  if (pictureList != null) {
    for (    String picture : pictureList) {
      keyValueList.add(new Entry<String,String>(picture,null));
    }
  }
  int pictureNum=keyValueList.size();
  gvMomentView.setVisibility(pictureNum <= 0 ? View.GONE : View.VISIBLE);
  if (pictureNum <= 0) {
    Log.i(TAG,"setList pictureNum <= 0 >> return;");
    return;
  }
  gvMomentView.setNumColumns(pictureNum <= 1 ? 1 : 3);
  if (adapter == null) {
    adapter=new GridAdapter(context).setHasName(false);
    gvMomentView.setAdapter(adapter);
  }
  adapter.refresh(keyValueList);
  gvMomentView.setOnItemClickListener(this);
  final int gridViewHeight=(int)(ScreenUtil.getScreenSize(context)[0] - convertView.getPaddingLeft() - convertView.getPaddingRight() - getDimension(R.dimen.moment_view_head_width));
  try {
    if (pictureNum >= 7) {
      gvMomentView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,gridViewHeight));
    }
 else     if (pictureNum >= 4) {
      gvMomentView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,(gridViewHeight * 2) / 3));
    }
 else     if (pictureNum >= 2) {
      gvMomentView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,gridViewHeight / 3));
    }
 else {
      gvMomentView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    }
  }
 catch (  Exception e) {
    Log.e(TAG," setPictureGrid  try int gridViewHeight;...>> catch" + e.getMessage());
  }
}

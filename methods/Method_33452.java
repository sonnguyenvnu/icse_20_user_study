private void setOnClick(final LinearLayout linearLayout,final AndroidBean bean){
  linearLayout.setOnClickListener(new PerfectClickListener(){
    @Override protected void onNoDoubleClick(    View v){
      WebViewActivity.loadUrl(v.getContext(),bean.getUrl(),bean.getDesc());
    }
  }
);
  linearLayout.setOnLongClickListener(new View.OnLongClickListener(){
    @Override public boolean onLongClick(    View v){
      String title=TextUtils.isEmpty(bean.getType()) ? bean.getDesc() : bean.getType() + "?  " + bean.getDesc();
      DialogBuild.showCustom(v,title,"????",new DialogInterface.OnClickListener(){
        @Override public void onClick(        DialogInterface dialog,        int which){
          WebViewActivity.loadUrl(linearLayout.getContext(),bean.getUrl(),bean.getDesc());
        }
      }
);
      return false;
    }
  }
);
}

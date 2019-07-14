private void showTagView(android.support.design.internal.FlowLayout flowLayout,final List<ArticlesBean> beanList){
  flowLayout.removeAllViews();
  for (int i=0; i < beanList.size(); i++) {
    TextView textView=(TextView)View.inflate(flowLayout.getContext(),R.layout.layout_navi_tag,null);
    textView.setTextColor(CommonUtils.randomColor());
    textView.setText(Html.fromHtml(beanList.get(i).getTitle()));
    flowLayout.addView(textView);
    int finalI=i;
    textView.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View view){
        WebViewActivity.loadUrl(view.getContext(),beanList.get(finalI).getLink(),beanList.get(finalI).getTitle());
      }
    }
);
  }
}

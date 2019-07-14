/** 
 * ?????????????
 */
private <T>void showTagView(android.support.design.internal.FlowLayout flowLayout,final List<T> beanList){
  flowLayout.removeAllViews();
  for (int i=0; i < beanList.size(); i++) {
    TextView textView=(TextView)View.inflate(flowLayout.getContext(),R.layout.layout_navi_tag,null);
    T bean=beanList.get(i);
    if (bean instanceof String) {
      textView.setText(Html.fromHtml((String)bean));
    }
 else     if (bean instanceof SearchTagBean.DataBean) {
      SearchTagBean.DataBean dataBean=(SearchTagBean.DataBean)bean;
      textView.setText(Html.fromHtml(dataBean.getName()));
    }
    flowLayout.addView(textView);
    textView.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View view){
        String name="";
        if (bean instanceof String) {
          name=(String)bean;
        }
 else         if (bean instanceof SearchTagBean.DataBean) {
          SearchTagBean.DataBean dataBean=(SearchTagBean.DataBean)bean;
          name=dataBean.getName();
        }
        viewModel.keyWord.set(name);
        viewModel.saveSearch(name);
        BaseTools.hideSoftKeyBoard(SearchActivity.this);
      }
    }
);
  }
}

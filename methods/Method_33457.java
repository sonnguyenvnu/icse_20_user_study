/** 
 * ????
 */
private void showTreeView(android.support.design.internal.FlowLayout flowLayout,final List<TreeItemBean.ChildrenBean> children,TreeItemBean dataBean){
  flowLayout.removeAllViews();
  for (int i=0; i < children.size(); i++) {
    TreeItemBean.ChildrenBean childrenBean=children.get(i);
    TextView textView=(TextView)View.inflate(flowLayout.getContext(),R.layout.layout_tree_tag,null);
    textView.setText(Html.fromHtml(childrenBean.getName()));
    flowLayout.addView(textView);
    textView.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View view){
        CategoryDetailActivity.start(view.getContext(),childrenBean.getId(),dataBean);
      }
    }
);
  }
}

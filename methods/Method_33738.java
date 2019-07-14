/** 
 * ?????????item????
 * @param isShowFirstDivider  ???item???????
 * @param isShowSecondDivider ???item???????
 */
public static void init(XRecyclerView recyclerView,boolean isShowFirstDivider,boolean isShowSecondDivider){
  recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
  recyclerView.setPullRefreshEnabled(false);
  recyclerView.clearHeader();
  recyclerView.setItemAnimator(null);
  MyDividerItemDecoration itemDecoration=new MyDividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL,false,isShowFirstDivider,isShowSecondDivider);
  itemDecoration.setDrawable(ContextCompat.getDrawable(recyclerView.getContext(),R.drawable.shape_line));
  recyclerView.addItemDecoration(itemDecoration);
}

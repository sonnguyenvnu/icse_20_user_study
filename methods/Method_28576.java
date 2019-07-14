private View initHeader(){
  View header=LayoutInflater.from(this).inflate(R.layout.item_covenientbanner_header,null);
  convenientBanner=(ConvenientBanner)header.findViewById(R.id.convenientBanner);
  loadTestDatas();
  convenientBanner.setPages(new CBViewHolderCreator(){
    @Override public LocalImageHolderView createHolder(    View itemView){
      return new LocalImageHolderView(itemView);
    }
    @Override public int getLayoutId(){
      return R.layout.item_localimage;
    }
  }
,localImages).setPageIndicator(new int[]{R.drawable.ic_page_indicator,R.drawable.ic_page_indicator_focused}).setOnItemClickListener(this);
  return header;
}

private void initData(){
  int cid=getIntent().getIntExtra("cid",0);
  TreeItemBean mTreeBean=(TreeItemBean)getIntent().getSerializableExtra("CategoryBean");
  bindingView.setTreeItemBean(mTreeBean);
  List<String> mTitleList=new ArrayList<>();
  List<Fragment> mFragments=new ArrayList<>();
  int initIndex=0;
  for (int i=0, len=mTreeBean.getChildren().size(); i < len; i++) {
    TreeItemBean.ChildrenBean childrenBean=mTreeBean.getChildren().get(i);
    mTitleList.add(childrenBean.getName());
    if (childrenBean.getId() == cid) {
      initIndex=i;
      mFragments.add(CategoryArticleFragment.newInstance(childrenBean.getId(),childrenBean.getName(),true));
    }
 else {
      mFragments.add(CategoryArticleFragment.newInstance(childrenBean.getId(),childrenBean.getName(),false));
    }
  }
  MyFragmentPagerAdapter myAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragments,mTitleList);
  bindingView.viewPager.setAdapter(myAdapter);
  myAdapter.notifyDataSetChanged();
  bindingView.tabLayout.setupWithViewPager(bindingView.viewPager);
  bindingView.viewPager.setCurrentItem(initIndex);
}

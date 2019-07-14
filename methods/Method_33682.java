private void initFragmentList(){
  mTitleList.clear();
  mTitleList.add("??");
  mTitleList.add("??");
  mTitleList.add("??");
  mFragments.add(CollectArticleFragment.newInstance());
  mFragments.add(CollectLinkFragment.newInstance());
  mFragments.add(JokeFragment.newInstance("??"));
}

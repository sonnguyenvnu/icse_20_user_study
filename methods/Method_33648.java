private void initData(){
  viewModel.history.observe(this,new Observer<List<String>>(){
    @Override public void onChanged(    @Nullable List<String> strings){
      if (strings != null && strings.size() > 0) {
        binding.clHistoryTag.setVisibility(View.VISIBLE);
        showTagView(binding.tflSearchHistory,strings);
      }
 else {
        binding.clHistoryTag.setVisibility(View.GONE);
      }
    }
  }
);
  viewModel.getHotkey().observe(this,new Observer<List<SearchTagBean.DataBean>>(){
    @Override public void onChanged(    @Nullable List<SearchTagBean.DataBean> dataBeans){
      if (dataBeans != null && dataBeans.size() > 0) {
        binding.clSearchTag.setVisibility(View.VISIBLE);
        showTagView(binding.tflSearch,dataBeans);
      }
 else {
        binding.clSearchTag.setVisibility(View.GONE);
      }
      viewModel.getHistoryData();
    }
  }
);
}

private void initHeaderView(HeaderItemBookBinding binding){
  binding.rb1.setOnCheckedChangeListener((buttonView,isChecked) -> refresh(isChecked,binding.rb1.getText().toString()));
  binding.rb2.setOnCheckedChangeListener((buttonView,isChecked) -> refresh(isChecked,binding.rb2.getText().toString()));
  binding.rb3.setOnCheckedChangeListener((buttonView,isChecked) -> refresh(isChecked,binding.rb3.getText().toString()));
  binding.rb4.setOnCheckedChangeListener((buttonView,isChecked) -> refresh(isChecked,binding.rb4.getText().toString()));
  binding.llTypeBook.setOnClickListener(v -> BookTypeActivity.start(this,viewModel.bookType.get()));
}

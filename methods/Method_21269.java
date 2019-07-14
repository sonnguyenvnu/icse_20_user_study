@Nullable @OnClick(R.id.back_button) protected void backButtonClick(){
  if (getContext() instanceof BaseActivity) {
    ((BaseActivity)getContext()).back();
  }
 else {
    ((AppCompatActivity)getContext()).onBackPressed();
  }
}

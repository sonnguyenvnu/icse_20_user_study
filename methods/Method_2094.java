@Override public void onViewCreated(View view,@Nullable Bundle savedInstanceState){
  SimpleDraweeView simpleDraweeView=(SimpleDraweeView)view.findViewById(R.id.drawee_view);
  simpleDraweeView.setImageURI(QUALIFIED_RESOURCE_URI);
}

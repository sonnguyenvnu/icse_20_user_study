public void bind(Address address){
  mNameTxt.setText(address.getName());
  mGenderTxt.setText(address.getGender().toString());
  mMobileTxt.setText(address.getPhone());
  mAddressTxt.setText(getString(R.string.label_address,address.getSummary(),address.getDetail()));
  mDeleteBtn.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      notifyItemAction(CLICK_TYPE_DELETE_BTN_CLICKED);
    }
  }
);
  mEditBtn.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      notifyItemAction(CLICK_TYPE_EDIT_BTN_CLICKED);
    }
  }
);
  itemView.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View view){
      notifyItemAction(CLICK_TYPE_ADDRESS_CLICKED);
    }
  }
);
}

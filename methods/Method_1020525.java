public void bind(Favorite favorite){
  Business business=favorite.getBusiness();
  if (business != null) {
    mPhotoImg.loadBusinessPhoto(business);
    mNameTxt.setText(business.getName());
    mMonthSalesTxt.setText(StringFetcher.getString(R.string.label_month_sales,business.getMonthSales()));
    mMultiContentTxt.setText(StringFetcher.getString(R.string.label_business_multi_content,String.valueOf(business.getMinPrice()),String.valueOf(business.getShippingFee()),String.valueOf(business.getShippingTime())));
    itemView.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View view){
        notifyItemAction(CLICK_TYPE_BUSINESS_CLICKED);
      }
    }
);
  }
}

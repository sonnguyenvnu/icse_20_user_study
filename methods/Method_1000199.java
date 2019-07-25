private void donate(int i){
  final String[] ids=getResources().getStringArray(DONATION_PRODUCT_IDS);
  billingProcessor.purchase(getActivity(),ids[i]);
}

/** 
 * Init the section header
 * @param section the section description
 */
public void setup(final AdapterSection section){
  mSection=section;
  setBackgroundColor(ThemeUtils.INSTANCE.getColor(getContext(),R.attr.vctr_list_header_background_color));
  setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
  View headerView=inflate(getContext(),R.layout.adapter_sticky_header,null);
  mTitleView=headerView.findViewById(R.id.section_title);
  mTitleView.setText(section.getTitle());
  mLoadingView=headerView.findViewById(R.id.section_loading);
  mLoadingView.setVisibility(View.INVISIBLE);
  if (section.getHeaderSubView() != -1) {
    mSubView=inflate(getContext(),section.getHeaderSubView(),null);
    LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
    params.addRule(RelativeLayout.BELOW,headerView.getId());
    mSubView.setLayoutParams(params);
    addView(mSubView);
  }
  addView(headerView);
}

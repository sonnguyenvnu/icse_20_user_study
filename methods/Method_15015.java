private void setSpan(SpannableString msp,final int index,int start,int end,final User user){
  msp.setSpan(new ClickableSpan(){
    @Override public void onClick(    View widget){
      onNameClick(index,widget,user);
    }
  }
,start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
}

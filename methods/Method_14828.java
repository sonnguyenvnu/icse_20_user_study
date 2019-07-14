@Override public void initEvent(){
  super.initEvent();
  findViewById(R.id.tvMomentSend).setOnClickListener(this);
  momentView.setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View v){
switch (v.getId()) {
case R.id.tvMomentViewContent:
        if (momentItem != null) {
          CommonUtil.copyText(context,momentItem.getMoment().getContent());
        }
      break;
case R.id.ivMomentViewComment:
    showInput(null);
  break;
default :
momentView.onClick(v);
break;
}
}
}
);
momentView.setOnDataChangedListener(new OnDataChangedListener(){
@Override public void onDataChanged(){
if (momentView.getStatus() == MomentItem.STATUS_DELETED) {
finish();
}
 else {
setHead(momentView.getData());
}
}
}
);
lvBaseList.setOnItemClickListener(new OnItemClickListener(){
@Override public void onItemClick(AdapterView<?> parent,View view,int position,long id){
onComemntItemClick(position,false);
}
}
);
lvBaseList.setOnItemLongClickListener(new OnItemLongClickListener(){
@Override public boolean onItemLongClick(AdapterView<?> parent,View view,int position,long id){
onComemntItemClick(position,true);
return true;
}
}
);
}

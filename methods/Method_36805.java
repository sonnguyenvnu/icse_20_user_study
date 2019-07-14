public void clearClickListener(View view,int eventType){
  view.setOnClickListener(null);
  innerClickMap.remove(view.hashCode());
}

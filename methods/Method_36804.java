public void setOnClickListener(View view,int eventType){
  view.setOnClickListener(this);
  innerClickMap.put(view.hashCode(),Integer.valueOf(eventType));
}

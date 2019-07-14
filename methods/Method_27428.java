@Override protected void onBindView(SimpleViewHolder<String> holder,int position){
  if (isLightTheme) {
    holder.itemView.setBackgroundColor(cardBackground);
  }
  String item=getItem(position);
  holder.itemView.setOnClickListener((view) -> {
    Intent intent=new Intent(new Intent(App.getInstance().getApplicationContext(),SearchActivity.class));
    intent.putExtra("search","topic:\"" + item + "\"");
    view.getContext().startActivity(intent);
  }
);
  holder.bind(getItem(position));
}

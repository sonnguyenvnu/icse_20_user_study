@Override public void onDestroyView(@NonNull ViewGroup container,int position,@NonNull View view){
  ViewHolder holder=(ViewHolder)view.getTag();
  GlideApp.with(holder.image).clear(holder.image);
  container.removeView(view);
}

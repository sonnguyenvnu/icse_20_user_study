private void animate(@NonNull VH holder,int position){
  if (isEnableAnimation() && position > lastKnowingPosition) {
    AnimHelper.startBeatsAnimation(holder.itemView);
    lastKnowingPosition=position;
  }
}

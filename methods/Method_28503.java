@SuppressWarnings("unchecked") private void onShowGuide(@NonNull VH holder,int position){
  if (position == 0 && !isShowedGuide() && guideListener != null) {
    guideListener.onShowGuide(holder.itemView,getItem(position));
    showedGuide=true;
  }
}

void reset(int newAdapterPosition){
  fullyVisible=false;
  visible=false;
  focusedVisible=false;
  adapterPosition=newAdapterPosition;
  lastVisibleHeightNotified=NOT_NOTIFIED;
  lastVisibleWidthNotified=NOT_NOTIFIED;
}

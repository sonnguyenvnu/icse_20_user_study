public static NotificationsViewHolder newInstance(@NonNull ViewGroup viewGroup,@Nullable BaseRecyclerAdapter adapter,boolean showUnreadState){
  return new NotificationsViewHolder(getView(viewGroup,R.layout.notifications_row_item),adapter,showUnreadState);
}

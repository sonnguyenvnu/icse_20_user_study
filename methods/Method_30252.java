protected void bindForumTopicListHolder(RecyclerView.ViewHolder holder,T item,List<SimpleItemForumTopic> forumTopicList){
  ForumTopicListHolder forumTopicListHolder=(ForumTopicListHolder)holder;
  forumTopicListHolder.createButton.setOnClickListener(view -> {
    UriHandler.open(item.url + "discussion/create",view.getContext());
  }
);
  forumTopicList=forumTopicList.subList(0,Math.min(ITEM_FORUM_TOPIC_LIST_MAX_SIZE,forumTopicList.size()));
  ViewUtils.setVisibleOrGone(forumTopicListHolder.forumTopicList,!forumTopicList.isEmpty());
  ItemForumTopicListAdapter adapter=(ItemForumTopicListAdapter)forumTopicListHolder.forumTopicList.getAdapter();
  adapter.replace(forumTopicList);
  forumTopicListHolder.viewMoreButton.setOnClickListener(view -> {
    UriHandler.open(item.url + "discussion",view.getContext());
  }
);
}

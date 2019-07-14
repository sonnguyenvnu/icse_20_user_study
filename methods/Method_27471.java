@Override public void bind(@NonNull Release item){
  title.setText(SpannableBuilder.builder().bold(!InputHelper.isEmpty(item.getName()) ? item.getName() : item.getTagName()));
  if (item.getAuthor() != null) {
    details.setText(SpannableBuilder.builder().append(item.getAuthor().getLogin()).append(" ").append(item.isDraft() ? drafted : released).append(" ").append(ParseDateFormat.getTimeAgo(item.getCreatedAt())));
  }
 else {
    details.setVisibility(View.GONE);
  }
}

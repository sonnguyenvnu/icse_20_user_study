private void appendGollumEvent(SpannableBuilder spannableBuilder,Event eventsModel){
  List<WikiModel> wiki=eventsModel.getPayload().getPages();
  if (wiki != null && !wiki.isEmpty()) {
    for (    WikiModel wikiModel : wiki) {
      spannableBuilder.bold(wikiModel.getAction()).append(" ").append(wikiModel.getPageName()).append(" ");
    }
  }
 else {
    spannableBuilder.bold(resources.getString(R.string.gollum)).append(" ");
  }
  spannableBuilder.append(eventsModel.getRepo().getName());
}

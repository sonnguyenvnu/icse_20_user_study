private void appendPushEvent(SpannableBuilder spannableBuilder,Event eventsModel){
  String ref=eventsModel.getPayload().getRef();
  if (ref.startsWith("refs/heads/")) {
    ref=ref.substring(11);
  }
  spannableBuilder.bold("pushed to").append(" ").append(ref).append(" ").bold("at").append(" ").append(eventsModel.getRepo().getName());
  final List<GitCommitModel> commits=eventsModel.getPayload().getCommits();
  int size=commits != null ? commits.size() : -1;
  SpannableBuilder spanCommits=SpannableBuilder.builder();
  if (size > 0) {
    if (size != 1)     spanCommits.append(String.valueOf(eventsModel.getPayload().getSize())).append(" new commits").append("\n");
 else     spanCommits.append("1 new commit").append("\n");
    int max=5;
    int appended=0;
    for (    GitCommitModel commit : commits) {
      if (commit == null)       continue;
      String sha=commit.getSha();
      if (TextUtils.isEmpty(sha))       continue;
      sha=sha.length() > 7 ? sha.substring(0,7) : sha;
      spanCommits.url(sha).append(" ").append(commit.getMessage() != null ? commit.getMessage().replaceAll("\\r?\\n|\\r"," ") : "").append("\n");
      appended++;
      if (appended == max)       break;
    }
  }
  if (spanCommits.length() > 0) {
    int last=spanCommits.length();
    description.setMaxLines(5);
    description.setText(spanCommits.delete(last - 1,last));
    description.setVisibility(View.VISIBLE);
  }
 else {
    description.setText("");
    description.setMaxLines(2);
    description.setVisibility(View.GONE);
  }
}

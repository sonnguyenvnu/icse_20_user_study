@Override public void process(Page page){
  page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
  page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+)").all());
  GithubRepo githubRepo=githubRepoPageMapper.get(page);
  if (githubRepo == null) {
    page.setSkip(true);
  }
 else {
    page.putField("repo",githubRepo);
  }
}

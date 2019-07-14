@Override public void process(Page page){
  page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
  page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+)").all());
  GithubRepo githubRepo=new GithubRepo();
  githubRepo.setAuthor(page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
  githubRepo.setName(page.getHtml().xpath("//h1[contains(@class, 'entry-title') and contains(@class, 'public')]/strong/a/text()").toString());
  githubRepo.setReadme(page.getHtml().xpath("//div[@id='readme']/tidyText()").toString());
  if (githubRepo.getName() == null) {
    page.setSkip(true);
  }
 else {
    page.putField("repo",githubRepo);
  }
}

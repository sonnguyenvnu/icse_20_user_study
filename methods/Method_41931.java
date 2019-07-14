private void initUi(){
  setSupportActionBar(toolbar);
  appVersion.setText(ApplicationUtils.getAppVersion());
  linkChangelog.setDescription(ApplicationUtils.getAppVersion());
  ArrayList<Contributor> contributors=new ArrayList<>(1);
  Contributor calvin=new Contributor(getString(R.string.developer_calvin_name),getString(R.string.about_developer_calvin_description),R.drawable.calvin_profile);
  calvin.setEmail(MAIL_CALVIN);
  calvin.addSocial(getString(R.string.google_plus_link),GOOGLE_ABOUT_CALVIN);
  calvin.addSocial(getString(R.string.github),GITHUB_CALVIN);
  contributors.add(calvin);
  ContributorsAdapter contributorsAdapter=new ContributorsAdapter(this,contributors,this);
  rvContributors.setHasFixedSize(true);
  rvContributors.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
  rvContributors.setAdapter(contributorsAdapter);
  ArrayList<Contact> donaldContacts=new ArrayList<>();
  donaldContacts.add(new Contact(TWITTER_ABOUT_DONALD,getString(R.string.twitter_link)));
  donaldContacts.add(new Contact(GITHUB_DONALD,getString(R.string.github_link)));
  aboutDonald.setupListeners(this,MAIL_DONALD,donaldContacts);
  ArrayList<Contact> jiboContacts=new ArrayList<>();
  jiboContacts.add(new Contact(TWITTER_ABOUT_GILBERT,getString(R.string.twitter_link)));
  jiboContacts.add(new Contact(GITHUB_GILBERT,getString(R.string.github_link)));
  aboutGilbert.setupListeners(this,MAIL_GILBERT,jiboContacts);
  aboutGilbert.setOnClickListener(v -> emojiEasterEgg());
  specialThanksPatryk.setMovementMethod(LinkMovementMethod.getInstance());
}

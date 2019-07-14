@Override public void init(Bundle savedInstanceState){
  showStatusBar(false);
  addSlide(AppIntroFragment.newInstance(getString(R.string.intro_title_1),getString(R.string.intro_description_1),R.drawable.intro_icon_1,Color.parseColor("#194673")));
  addSlide(AppIntroFragment.newInstance(getString(R.string.intro_title_2),getString(R.string.intro_description_2),R.drawable.intro_icon_2,Color.parseColor("#ffa726")));
  addSlide(AppIntroFragment.newInstance(getString(R.string.intro_title_4),getString(R.string.intro_description_4),R.drawable.intro_icon_4,Color.parseColor("#9575cd")));
}

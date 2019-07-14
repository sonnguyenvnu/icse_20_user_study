private void emojiEasterEgg(){
  emojiEasterEggCount++;
  if (emojiEasterEggCount > 3) {
    boolean showEasterEgg=Prefs.showEasterEgg();
    Toast.makeText(this,(!showEasterEgg ? this.getString(R.string.easter_egg_enable) : this.getString(R.string.easter_egg_disable)) + " " + this.getString(R.string.emoji_easter_egg),Toast.LENGTH_SHORT).show();
    Prefs.setShowEasterEgg(!showEasterEgg);
    emojiEasterEggCount=0;
  }
}

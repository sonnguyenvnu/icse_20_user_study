public static Intent makeIntent(Book item,Context context){
  return new Intent(context,TableOfContentsActivity.class).putExtra(EXTRA_BOOK,item);
}

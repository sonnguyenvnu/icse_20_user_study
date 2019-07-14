public static Intent makeIntent(long bookId,Context context){
  return new Intent(context,BookActivity.class).putExtra(EXTRA_BOOK_ID,bookId);
}

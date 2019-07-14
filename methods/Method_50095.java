@Mutation("seed") Empty seed(ShelfServiceGrpc.ShelfServiceBlockingStub shelfClient,BookServiceGrpc.BookServiceBlockingStub bookClient){
  String greatExpectations=bookClient.createBook(CreateBookRequest.newBuilder().setBook(Book.newBuilder().setAuthor("Charles Dickens").setTitle("Great Expectations").setRead(false)).build()).getId();
  String thinkingFastAndSlow=bookClient.createBook(CreateBookRequest.newBuilder().setBook(Book.newBuilder().setAuthor("Daniel Kahnemann").setTitle("Thinking, Fast and Slow").setRead(true)).build()).getId();
  String theCatcherInTheRye=bookClient.createBook(CreateBookRequest.newBuilder().setBook(Book.newBuilder().setAuthor("J. D. Salinger").setTitle("The Catcher in the Rye").setRead(false)).build()).getId();
  String huckleberryFinn=bookClient.createBook(CreateBookRequest.newBuilder().setBook(Book.newBuilder().setAuthor("Mark Twain").setTitle("The Adventures of Huckleberry Finn").setRead(false)).build()).getId();
  String masterAndMargarita=bookClient.createBook(CreateBookRequest.newBuilder().setBook(Book.newBuilder().setAuthor("Mikhail Bulgakov").setTitle("The Master and Margarita").setRead(false)).build()).getId();
  String warAndPeace=bookClient.createBook(CreateBookRequest.newBuilder().setBook(Book.newBuilder().setAuthor("Leo Tolstoy").setTitle("War and Peace").setRead(true)).build()).getId();
  shelfClient.createShelf(CreateShelfRequest.newBuilder().setShelf(Shelf.newBuilder().setTheme("Satire").addAllBookIds(ImmutableList.of(greatExpectations,thinkingFastAndSlow)).build()).build());
  shelfClient.createShelf(CreateShelfRequest.newBuilder().setShelf(Shelf.newBuilder().setTheme("Classics").addAllBookIds(ImmutableList.of(theCatcherInTheRye,huckleberryFinn)).build()).build());
  shelfClient.createShelf(CreateShelfRequest.newBuilder().setShelf(Shelf.newBuilder().setTheme("Russian").addAllBookIds(ImmutableList.of(masterAndMargarita,warAndPeace)).build()).build());
  return Empty.getDefaultInstance();
}

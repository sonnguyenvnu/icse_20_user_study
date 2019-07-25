/** 
 * @describe ????
 * @author zc
 * @version 1.0 2017-09-15
 */
@PostMapping("/add/book/novel") public ResponseEntity<?> add(@RequestParam(name="title") String title,@RequestParam(name="author") String author,@RequestParam(name="word_count") int wordCount,@RequestParam(name="publish_date") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date publishDate){
  try {
    XContentBuilder content=XContentFactory.jsonBuilder().startObject().field("title",title).field("author",author).field("word_count",wordCount).field("publish_date",publishDate.getTime()).endObject();
    IndexResponse result=this.client.prepareIndex("book","novel").setSource(content).get();
    return new ResponseEntity<>(result.getId(),HttpStatus.OK);
  }
 catch (  IOException e) {
    e.printStackTrace();
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

/** 
 * Initializes parser.
 */
@Override protected void initialize(final char[] input){
  super.initialize(input);
  this.tag=new ParsedTag();
  this.doctype=new ParsedDoctype();
  this.text=new char[1024];
  this.textLen=0;
  this.parsingTime=-1;
}

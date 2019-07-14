/** 
 * Starts the tag with the index of first '<'. Resets all tag data.
 */
public void start(final int startIndex){
  this.tagStartIndex=startIndex;
  this.name=null;
  this.idNdx=-1;
  this.attributesCount=0;
  this.tagLength=0;
  this.modified=false;
  this.type=TagType.START;
  this.rawTag=false;
}

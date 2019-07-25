/** 
 * Sets all the request flags.
 */
public NodesStatsRequest all(){
  this.indices.all();
  this.os=true;
  this.process=true;
  this.jvm=true;
  this.threadPool=true;
  this.fs=true;
  this.transport=true;
  this.http=true;
  this.breaker=true;
  this.script=true;
  this.discovery=true;
  this.ingest=true;
  this.adaptiveSelection=true;
  return this;
}

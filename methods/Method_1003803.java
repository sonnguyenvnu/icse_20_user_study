/** 
 * Enable the reporting of Hystrix metrics via SSE. <p> To stream metrics within an application see  {@link HystrixMetricsEventStreamHandler}.
 * @return this {@code HystrixModule}
 */
public HystrixModule sse(){
  this.reportMetricsToSse=true;
  return this;
}

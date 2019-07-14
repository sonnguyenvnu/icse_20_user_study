/** 
 * Should be called when the Object at top changes values. Still log(n) worst case, but it's at least twice as fast to <pre class="prettyprint"> pq.top().change(); pq.updateTop(); </pre> instead of <pre class="prettyprint"> o = pq.pop(); o.change(); pq.push(o); </pre>
 * @return the new 'top' element.
 */
public final T updateTop(){
  downHeap();
  return heap[1];
}

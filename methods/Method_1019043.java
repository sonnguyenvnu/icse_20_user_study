/** 
 * Discover regions and mark their ranges.
 */
public void analyze(){
  populateLookups();
  markClassRanges();
  markMemberRanges();
  markOtherRanges();
}

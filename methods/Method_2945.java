/** 
 * ??863???<br> 863????????????????? <p> Tag	Description	Example	Tag	Description	Example a	adjective	??	ni	organization name	???? b	other noun-modifier	??, ??	nl	location noun	?? c	conjunction	?, ??	ns	geographical name	?? d	adverb	?	nt	temporal noun	??, ?? e	exclamation	?	nz	other proper noun	???? g	morpheme	?, ?	o	onomatopoeia	?? h	prefix	?, ?	p	preposition	?, ? i	idiom	????	q	quantity	? j	abbreviation	???	r	pronoun	?? k	suffix	?, ?	u	auxiliary	?, ? m	number	?, ??	v	verb	?, ?? n	general noun	??	wp	punctuation	??? nd	direction noun	??	ws	foreign words	CPU nh	person name	??, ??	x	non-lexeme	?, ?
 * @param termList
 * @return
 */
public static List<String> to863(List<Term> termList){
  List<String> posTagList=new ArrayList<String>(termList.size());
  for (  Term term : termList) {
    String posTag=posConverter.get(term.nature.toString());
    if (posTag == null)     posTag=term.nature.toString();
    posTagList.add(posTag);
  }
  return posTagList;
}

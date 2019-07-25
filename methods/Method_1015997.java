private QueryBuilder parse(String q,int timezoneOffset){
  q=q.replaceAll(" AND "," ");
  boolean ORconnective=q.indexOf(" OR ") >= 0;
  q=q.replaceAll(" OR "," ");
  Set<String> qe=new LinkedHashSet<String>();
  Matcher m=tokenizerPattern.matcher(q);
  while (m.find())   qe.add(m.group(1));
  ArrayList<String> text_positive_match=new ArrayList<>();
  ArrayList<String> text_negative_match=new ArrayList<>();
  ArrayList<String> text_positive_filter=new ArrayList<>();
  ArrayList<String> text_negative_filter=new ArrayList<>();
  Multimap<String,String> modifier=HashMultimap.create();
  Set<String> constraints_positive=new HashSet<>();
  Set<String> constraints_negative=new HashSet<>();
  for (  String t : qe) {
    if (t.length() == 0)     continue;
    if (t.startsWith("/")) {
      constraints_positive.add(t.substring(1));
      continue;
    }
 else     if (t.startsWith("-/")) {
      constraints_negative.add(t.substring(2));
      continue;
    }
 else     if (t.indexOf(':') > 0) {
      int p=t.indexOf(':');
      String name=t.substring(0,p).toLowerCase();
      String value=t.substring(p + 1);
      if (value.indexOf('|') > 0) {
        String[] values=value.split("\\|");
        for (        String v : values) {
          modifier.put(name,v);
        }
      }
 else {
        modifier.put(name,value);
      }
      continue;
    }
 else {
      boolean negative=t.startsWith("-");
      if (negative)       t=t.substring(1);
      if (t.length() == 0)       continue;
      if ((t.charAt(0) == dq && t.charAt(t.length() - 1) == dq) || (t.charAt(0) == sq && t.charAt(t.length() - 1) == sq)) {
        t=t.substring(1,t.length() - 1);
        if (negative) {
          text_negative_filter.add(t);
          this.negativeBag.add(t);
        }
 else {
          text_positive_filter.add(t);
          this.positiveBag.add(t);
        }
      }
 else       if (t.indexOf('-') > 0) {
        t=t.replace('-',space);
        if (negative) {
          text_negative_filter.add(t);
          this.negativeBag.add(t);
        }
 else {
          text_positive_filter.add(t);
          this.positiveBag.add(t);
        }
      }
 else {
        if (negative) {
          text_negative_match.add(t);
          this.negativeBag.add(t);
        }
 else {
          text_positive_match.add(t);
          this.positiveBag.add(t);
        }
      }
      continue;
    }
  }
  if (modifier.containsKey("boost")) {
    this.boosts.patchWithModifier(modifier.get("boost").iterator().next());
  }
  List<QueryBuilder> queries=new ArrayList<>();
  if (!text_positive_match.isEmpty())   queries.add(simpleQueryBuilder(String.join(" ",text_positive_match),ORconnective,boosts));
  if (!text_negative_match.isEmpty())   queries.add(QueryBuilders.boolQuery().mustNot(simpleQueryBuilder(String.join(" ",text_negative_match),ORconnective,boosts)));
  for (  String text : text_positive_filter) {
    queries.add(exactMatchQueryBuilder(text,this.boosts));
  }
  for (  String text : text_negative_filter) {
    queries.add(QueryBuilders.boolQuery().mustNot(exactMatchQueryBuilder(text,this.boosts)));
  }
  Collection<String> values;
  modifier_handling:   for (  String[] modifierType : modifierTypes) {
    String modifier_name=modifierType[0];
    String index_name=modifierType[1];
    if ((values=modifier.get(modifier_name)).size() > 0) {
      if (modifier_name.equals("yacy")) {
        values.forEach(y -> this.yacyModifiers.add(y));
        continue modifier_handling;
      }
      if (modifier_name.equals("site") && values.size() == 1) {
        String host=values.iterator().next();
        if (host.startsWith("www."))         values.add(host.substring(4));
 else         values.add("www." + host);
      }
      queries.add(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery(index_name,values)));
      continue modifier_handling;
    }
    if ((values=modifier.get("-" + modifier_name)).size() > 0) {
      if (modifier_name.equals("site") && values.size() == 1) {
        String host=values.iterator().next();
        if (host.startsWith("www."))         values.add(host.substring(4));
 else         values.add("www." + host);
      }
      queries.add(QueryBuilders.boolQuery().mustNot(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery(index_name,values))));
      continue modifier_handling;
    }
  }
  if (modifier.containsKey("collection") && (this.collections == null || this.collections.length == 0)) {
    Collection<String> c=modifier.get("collection");
    this.collections=c.toArray(new String[c.size()]);
  }
  if (modifier.containsKey("daterange")) {
    String dr=modifier.get("daterange").iterator().next();
    if (dr.length() > 0) {
      String from_to[]=dr.endsWith("..") ? new String[]{dr.substring(0,dr.length() - 2),""} : dr.startsWith("..") ? new String[]{"",dr.substring(2)} : dr.split("\\.\\.");
      if (from_to.length == 2) {
        if (from_to[0] != null && from_to[0].length() > 0)         try {
          modifier.put("since",DateParser.dayDateFormat.format(DateParser.parse(from_to[0],timezoneOffset).getTime()));
        }
 catch (        ParseException e) {
        }
        if (from_to[1] != null && from_to[1].length() > 0)         try {
          modifier.put("until",DateParser.dayDateFormat.format(DateParser.parse(from_to[1],timezoneOffset).getTime()));
        }
 catch (        ParseException e) {
        }
      }
    }
  }
  if (modifier.containsKey("since"))   try {
    Calendar since=DateParser.parse(modifier.get("since").iterator().next(),timezoneOffset);
    this.since=since.getTime();
    RangeQueryBuilder rangeQuery=QueryBuilders.rangeQuery(WebMapping.last_modified.getMapping().name()).from(DateParser.formatGSAFS(this.since));
    if (modifier.containsKey("until")) {
      Calendar until=DateParser.parse(modifier.get("until").iterator().next(),timezoneOffset);
      if (until.get(Calendar.HOUR) == 0 && until.get(Calendar.MINUTE) == 0) {
        until.add(Calendar.DATE,1);
      }
      this.until=until.getTime();
      rangeQuery.to(DateParser.formatGSAFS(this.until));
    }
 else {
      this.until=new Date(Long.MAX_VALUE);
    }
    queries.add(rangeQuery);
  }
 catch (  ParseException e) {
  }
 else   if (modifier.containsKey("until"))   try {
    Calendar until=DateParser.parse(modifier.get("until").iterator().next(),timezoneOffset);
    if (until.get(Calendar.HOUR) == 0 && until.get(Calendar.MINUTE) == 0) {
      until.add(Calendar.DATE,1);
    }
    this.until=until.getTime();
    RangeQueryBuilder rangeQuery=QueryBuilders.rangeQuery(WebMapping.last_modified.getMapping().name()).to(DateParser.formatGSAFS(this.until));
    queries.add(rangeQuery);
  }
 catch (  ParseException e) {
  }
  if (queries.size() == 1) {
    return queries.iterator().next();
  }
  BoolQueryBuilder b=QueryBuilders.boolQuery();
  for (  QueryBuilder filter : queries) {
    if (ORconnective)     b.should(filter);
 else     b.must(filter);
  }
  if (ORconnective)   b.minimumShouldMatch(1);
  return b;
}

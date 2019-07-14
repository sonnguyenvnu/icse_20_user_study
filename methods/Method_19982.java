@Exclude public Map<String,Object> toMap(){
  HashMap<String,Object> result=new HashMap<>();
  result.put("uid",uid);
  result.put("author",author);
  result.put("title",title);
  result.put("body",body);
  result.put("starCount",starCount);
  result.put("stars",stars);
  return result;
}

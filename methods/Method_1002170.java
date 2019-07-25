private Uri[] parse(JSONArray urls){
  List<Uri> res=new ArrayList<>();
  for (int i=0; i < urls.length(); i++) {
    String url=null;
    try {
      url=urls.getString(i);
    }
 catch (    JSONException e) {
      e.printStackTrace();
    }
    if (url != null)     res.add(Uri.parse(url));
  }
  return res.toArray(new Uri[]{});
}

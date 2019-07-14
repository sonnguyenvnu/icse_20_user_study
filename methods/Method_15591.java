/** 
 * ?request?response??????callback??
 * @param target
 * @param request
 * @param callback
 * @param creator 
 * @return
 * @throws Exception 
 */
public static JSONObject parse(String name,JSONObject target,JSONObject real,SQLCreator creator,@NotNull OnParseCallback callback) throws Exception {
  if (target == null) {
    return null;
  }
  JSONObject type=target.getJSONObject(TYPE.name());
  JSONObject verify=target.getJSONObject(VERIFY.name());
  JSONObject insert=target.getJSONObject(INSERT.name());
  JSONObject add=target.getJSONObject(ADD.name());
  JSONObject update=target.getJSONObject(UPDATE.name());
  JSONObject put=target.getJSONObject(PUT.name());
  JSONObject replace=target.getJSONObject(REPLACE.name());
  String unique=StringUtil.getNoBlankString(target.getString(UNIQUE.name()));
  String remove=StringUtil.getNoBlankString(target.getString(REMOVE.name()));
  String necessary=StringUtil.getNoBlankString(target.getString(NECESSARY.name()));
  String disallow=StringUtil.getNoBlankString(target.getString(DISALLOW.name()));
  target.remove(TYPE.name());
  target.remove(VERIFY.name());
  target.remove(INSERT.name());
  target.remove(ADD.name());
  target.remove(UPDATE.name());
  target.remove(PUT.name());
  target.remove(REPLACE.name());
  target.remove(UNIQUE.name());
  target.remove(REMOVE.name());
  target.remove(NECESSARY.name());
  target.remove(DISALLOW.name());
  String[] removes=StringUtil.split(remove);
  if (removes != null && removes.length > 0) {
    for (    String r : removes) {
      real.remove(r);
    }
  }
  String[] necessarys=StringUtil.split(necessary);
  List<String> necessaryList=necessarys == null ? new ArrayList<String>() : Arrays.asList(necessarys);
  for (  String s : necessaryList) {
    if (real.get(s) == null) {
      throw new IllegalArgumentException(name + " ?????? " + s + " ?[" + necessary + "]???????");
    }
  }
  Set<String> objKeySet=new HashSet<String>();
  Set<Entry<String,Object>> set=new LinkedHashSet<>(target.entrySet());
  if (set.isEmpty() == false) {
    String key;
    Object tvalue;
    Object rvalue;
    for (    Entry<String,Object> entry : set) {
      key=entry == null ? null : entry.getKey();
      if (key == null) {
        continue;
      }
      tvalue=entry.getValue();
      rvalue=real.get(key);
      if (callback.onParse(key,tvalue,rvalue) == false) {
        continue;
      }
      if (tvalue instanceof JSONObject) {
        tvalue=callback.onParseJSONObject(key,(JSONObject)tvalue,(JSONObject)rvalue);
        objKeySet.add(key);
      }
 else       if (tvalue instanceof JSONArray) {
        tvalue=callback.onParseJSONArray(key,(JSONArray)tvalue,(JSONArray)rvalue);
      }
 else {
        tvalue=callback.onParseObject(key,tvalue,rvalue);
      }
      if (tvalue != null) {
        real.put(key,tvalue);
      }
    }
  }
  Set<String> rkset=real.keySet();
  List<String> disallowList=new ArrayList<String>();
  if ("!".equals(disallow)) {
    for (    String key : rkset) {
      if (key != null && key.startsWith("@") == false && necessaryList.contains(key) == false && objKeySet.contains(key) == false) {
        disallowList.add(key);
      }
    }
  }
 else {
    String[] disallows=StringUtil.split(disallow);
    if (disallows != null && disallows.length > 0) {
      disallowList.addAll(Arrays.asList(disallows));
    }
  }
  for (  String rk : rkset) {
    if (disallowList.contains(rk)) {
      throw new IllegalArgumentException(name + " ?????? " + rk + " ?" + StringUtil.getString(disallowList) + "???????");
    }
    if (rk == null) {
      real.remove(rk);
      continue;
    }
    if (rk.startsWith("@") == false && objKeySet.contains(rk) == false && real.get(rk) instanceof JSONObject) {
      throw new UnsupportedOperationException(name + " ?????? " + rk + ":{} ?");
    }
  }
  real=operate(TYPE,type,real,creator);
  real=operate(VERIFY,verify,real,creator);
  real=operate(INSERT,insert,real,creator);
  real=operate(ADD,add,real,creator);
  real=operate(UPDATE,update,real,creator);
  real=operate(PUT,put,real,creator);
  real=operate(REPLACE,replace,real,creator);
  String[] uniques=StringUtil.split(unique);
  if (uniques != null && uniques.length > 0) {
    long exceptId=real.getLongValue(KEY_ID);
    for (    String u : uniques) {
      verifyRepeat(name,u,real.get(u),exceptId,creator);
    }
  }
  Log.i(TAG,"parse  return real = " + JSON.toJSONString(real));
  return real;
}

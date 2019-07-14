/** 
 * ???????????
 * @return
 */
public String getHtml(){
  if (query != null) {
    String params="";
    String[] querys=query.split("&");
    for (int i=0; i < querys.length; i++) {
      if (querys[i].startsWith(param)) {
        continue;
      }
      if ("".equals(params)) {
        params=querys[i];
      }
 else {
        params+="&" + querys[i];
      }
    }
    if (!"".equals(params)) {
      url+="?" + params;
    }
  }
  String pages="";
  int pageCount=(int)Math.ceil((double)total / rows);
  if (pageCount <= 1) {
    return pages;
  }
  if (page > pageCount) {
    page=pageCount;
  }
  if (page <= 0) {
    page=1;
  }
  if (page > 1) {
    if (url.contains("?")) {
      pages=pages.concat("<a class=\"prev\" href=\"" + url + "&" + param + "=" + (page - 1) + "\">???</a>\n");
    }
 else {
      pages=pages.concat("<a class=\"prev\" href=\"" + url + "?" + param + "=" + (page - 1) + "\">???</a>\n");
    }
  }
 else {
    pages=pages.concat("<a class=\"prev\" href=\"javascript:;\" style=\"color:#ccc\">???</a>\n");
  }
  if (pageCount > step) {
    int listBegin=(page - (int)Math.floor((double)step / 2));
    if (listBegin < 1) {
      listBegin=1;
    }
    if (listBegin >= 2) {
      if (url.contains("?")) {
        pages=pages.concat("<a href=\"" + url + "&" + param + "=1\">1</a> ... \n");
      }
 else {
        pages=pages.concat("<a href=\"" + url + "?" + param + "=1\">1</a> ... \n");
      }
    }
    if (pageCount - page >= page - listBegin) {
      for (int i=listBegin; i < (listBegin + step); i++) {
        if (i != page) {
          if (url.contains("?")) {
            pages=pages.concat("<a href=\"" + url + "&" + param + "=" + i + "\">" + i + "</a>\n");
          }
 else {
            pages=pages.concat("<a href=\"" + url + "?" + param + "=" + i + "\">" + i + "</a>\n");
          }
        }
 else {
          pages=pages.concat("<span class=\"current\">" + i + "</span>\n");
        }
      }
      if (listBegin + step <= pageCount) {
        if (url.contains("?")) {
          pages=pages.concat(" ... <a href=\"" + url + "&" + param + "=" + pageCount + "\">" + pageCount + "</a>\n");
        }
 else {
          pages=pages.concat(" ... <a href=\"" + url + "?" + param + "=" + pageCount + "\">" + pageCount + "</a>\n");
        }
      }
    }
 else {
      for (int i=(pageCount - step) + 1; i <= pageCount; i++) {
        if (i != page) {
          if (url.contains("?")) {
            pages=pages.concat("<a href=\"" + url + "&" + param + "=" + i + "\">" + i + "</a>\n");
          }
 else {
            pages=pages.concat("<a href=\"" + url + "?" + param + "=" + i + "\">" + i + "</a>\n");
          }
        }
 else {
          pages=pages.concat("<span class=\"current\">" + i + "</span>\n");
        }
      }
    }
  }
 else {
    for (int i=1; i <= pageCount; i++) {
      if (i != page) {
        if (url.contains("?")) {
          pages=pages.concat("<a href=\"" + url + "&" + param + "=" + i + "\">" + i + "</a>\n");
        }
 else {
          pages=pages.concat("<a href=\"" + url + "?" + param + "=" + i + "\">" + i + "</a>\n");
        }
      }
 else {
        pages=pages.concat("<span class=\"current\">" + i + "</span>\n");
      }
    }
  }
  if (page < pageCount) {
    if (url.contains("?")) {
      pages=pages.concat("<a class=\"next\" href=\"" + url + "&" + param + "=" + (page + 1) + "\">???</a>\n");
    }
 else {
      pages=pages.concat("<a class=\"next\" href=\"" + url + "?" + param + "=" + (page + 1) + "\">???</a>\n");
    }
  }
 else {
    pages=pages.concat("<a class=\"next\" href=\"javascript:;\" style=\"color:#ccc\">???</a>\n");
  }
  return pages;
}

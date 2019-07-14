@Override public void doAdvice(final RequestContext context) throws RequestProcessAdviceException {
  final HttpServletRequest request=context.getRequest();
  final String requestURI=context.requestURI();
  final String[] skips=Symphonys.ANONYMOUS_VIEW_SKIPS.split(",");
  for (  final String skip : skips) {
    if (AntPathMatcher.match(Latkes.getContextPath() + skip,requestURI)) {
      return;
    }
  }
  final JSONObject exception404=new JSONObject();
  exception404.put(Keys.MSG,HttpServletResponse.SC_NOT_FOUND + ", " + context.requestURI());
  exception404.put(Keys.STATUS_CODE,HttpServletResponse.SC_NOT_FOUND);
  final JSONObject exception401=new JSONObject();
  exception401.put(Keys.MSG,HttpServletResponse.SC_UNAUTHORIZED + ", " + context.requestURI());
  exception401.put(Keys.STATUS_CODE,HttpServletResponse.SC_UNAUTHORIZED);
  if (requestURI.startsWith(Latkes.getContextPath() + "/article/")) {
    final String articleId=StringUtils.substringAfter(requestURI,Latkes.getContextPath() + "/article/");
    try {
      final JSONObject article=articleRepository.get(articleId);
      if (null == article) {
        throw new RequestProcessAdviceException(exception404);
      }
      if (Article.ARTICLE_ANONYMOUS_VIEW_C_NOT_ALLOW == article.optInt(Article.ARTICLE_ANONYMOUS_VIEW) && !Sessions.isLoggedIn()) {
        throw new RequestProcessAdviceException(exception401);
      }
 else       if (Article.ARTICLE_ANONYMOUS_VIEW_C_ALLOW == article.optInt(Article.ARTICLE_ANONYMOUS_VIEW)) {
        return;
      }
    }
 catch (    final RepositoryException e) {
      LOGGER.log(Level.ERROR,"Get article [id=" + articleId + "] failed",e);
      throw new RequestProcessAdviceException(exception404);
    }
  }
  try {
    final JSONObject option=optionQueryService.getOption(Option.ID_C_MISC_ALLOW_ANONYMOUS_VIEW);
    if (!"0".equals(option.optString(Option.OPTION_VALUE))) {
      final JSONObject currentUser=Sessions.getUser();
      final String cookieNameVisits="anonymous-visits";
      final Cookie visitsCookie=getCookie(request,cookieNameVisits);
      if (null == currentUser) {
        if (null != visitsCookie) {
          final JSONArray uris=new JSONArray(URLs.decode(visitsCookie.getValue()));
          for (int i=0; i < uris.length(); i++) {
            final String uri=uris.getString(i);
            if (uri.equals(requestURI)) {
              return;
            }
          }
          uris.put(requestURI);
          if (uris.length() > Symphonys.ANONYMOUS_VIEW_URIS) {
            throw new RequestProcessAdviceException(exception401);
          }
          addCookie(context.getResponse(),cookieNameVisits,URLs.encode(uris.toString()));
          return;
        }
 else {
          final JSONArray uris=new JSONArray();
          uris.put(requestURI);
          addCookie(context.getResponse(),cookieNameVisits,URLs.encode(uris.toString()));
          return;
        }
      }
 else {
        if (null != visitsCookie) {
          final Cookie cookie=new Cookie(cookieNameVisits,null);
          cookie.setMaxAge(0);
          cookie.setPath("/");
          context.getResponse().addCookie(cookie);
        }
      }
    }
  }
 catch (  final RequestProcessAdviceException e) {
    throw e;
  }
catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Anonymous view check failed: " + e.getMessage());
    throw new RequestProcessAdviceException(exception401);
  }
}

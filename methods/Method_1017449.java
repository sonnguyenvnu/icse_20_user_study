/** 
 * ????
 * @param datacubeType ??????
 * @param beginDate ??????????begin_date?end_date??????“??????”??????????1?? begin_date?end_date??????0?????1???????
 * @param endDate ??????????end_date???????????
 * @see com.foxinmy.weixin4j.mp.api.DataApi
 * @see com.foxinmy.weixin4j.mp.datacube.UserSummary
 * @see com.foxinmy.weixin4j.mp.datacube.ArticleSummary
 * @see com.foxinmy.weixin4j.mp.datacube.ArticleTotal
 * @see com.foxinmy.weixin4j.mp.datacube.ArticleDatacubeShare
 * @see com.foxinmy.weixin4j.mp.datacube.UpstreamMsg
 * @see com.foxinmy.weixin4j.mp.datacube.UpstreamMsgDist
 * @see com.foxinmy.weixin4j.mp.datacube.InterfaceSummary
 * @return ????
 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141082&token=&lang=zh_CN">
	 *      ????</a>
 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">
	 *      ????</a>
 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">
	 *      ????</a>
 * @see <a href=
	 *      "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141086&token=&lang=zh_CN">
	 *      ????</a>
 * @throws WeixinException
 */
public List<?> datacube(DatacubeType datacubeType,Date beginDate,Date endDate) throws WeixinException {
  return dataApi.datacube(datacubeType,beginDate,endDate);
}

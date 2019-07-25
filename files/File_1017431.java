package com.foxinmy.weixin4j.mp.api;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.WeixinResponse;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.mp.type.DatacubeType;
import com.foxinmy.weixin4j.token.TokenManager;
import com.foxinmy.weixin4j.util.DateUtil;

/**
 * 数�?�分�?API
 * <p>
 * 1�?接�?�侧的公众�?�数�?�的数�?�库中仅存储了2014年12月1日之�?�的数�?�，将查询�?到在此之�?的日期，�?�使有查到，也是�?�?�信的�?数�?�；</br>
 * 2�?请开�?�者在调用接�?�获�?�数�?��?�，将数�?��?存在自身数�?�库中，�?�加快下次用户的访问速度，也�?低了微信侧接�?�调用的�?必�?�?�耗。</br>
 * </p>
 * 
 * @className DataApi
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月7日
 * @since JDK 1.6
 * @see
 */
public class DataApi extends MpApi {
	private final TokenManager tokenManager;

	public DataApi(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}

	/**
	 * 数�?�统计
	 * 
	 * @param datacubeType
	 *            统计类型
	 * @param beginDate
	 *            开始日期
	 * @param offset
	 *            增�? 表示�?��?几天 比如 offset=1 则查询 beginDate的�?�一天之间的数�?�
	 * @see {@link #datacube(DatacubeType, Date,Date)}
	 * @throws WeixinException
	 */
	public List<?> datacube(DatacubeType datacubeType, Date beginDate,
			int offset) throws WeixinException {
		Calendar ca = Calendar.getInstance();
		ca.setTime(beginDate);
		ca.add(Calendar.DAY_OF_MONTH, offset);
		return datacube(datacubeType, beginDate, ca.getTime());
	}

	/**
	 * 数�?�统计
	 * 
	 * @param datacubeType
	 *            统计类型
	 * @param offset
	 *            增�? 表示�?��?�几天 比如 offset=1 则查询 beginDate的�?一天之间的数�?�
	 * @param endDate
	 *            截至日期
	 * @see {@link #datacube(DatacubeType, Date,Date)}
	 * @throws WeixinException
	 */
	public List<?> datacube(DatacubeType datacubeType, int offset, Date endDate)
			throws WeixinException {
		Calendar ca = Calendar.getInstance();
		ca.setTime(endDate);
		ca.add(Calendar.DAY_OF_MONTH, 0 - offset);
		return datacube(datacubeType, ca.getTime(), endDate);
	}

	/**
	 * 查询日期跨度为0的统计数�?�(当天)
	 * 
	 * @param datacubeType
	 *            统计类型
	 * @param date
	 *            统计日期
	 * @see {@link #datacube(DatacubeType, Date,Date)}
	 * @throws WeixinException
	 */
	public List<?> datacube(DatacubeType datacubeType, Date date)
			throws WeixinException {
		return datacube(datacubeType, date, date);
	}

	/**
	 * 数�?�统计
	 * 
	 * @param datacubeType
	 *            数�?�统计类型
	 * @param beginDate
	 *            获�?�数�?�的起始日期，begin_date和end_date的差值需�?于“最大时间跨度�?（比如最大时间跨度为1时，
	 *            begin_date和end_date的差值�?�能为0，�?能�?于1），�?�则会报错
	 * @param endDate
	 *            获�?�数�?�的结�?�日期，end_date�?许设置的最大值为昨日
	 * @see com.foxinmy.weixin4j.mp.datacube.UserSummary
	 * @see com.foxinmy.weixin4j.mp.datacube.ArticleSummary
	 * @see com.foxinmy.weixin4j.mp.datacube.ArticleTotal
	 * @see com.foxinmy.weixin4j.mp.datacube.ArticleDatacubeShare
	 * @see com.foxinmy.weixin4j.mp.datacube.UpstreamMsg
	 * @see com.foxinmy.weixin4j.mp.datacube.UpstreamMsgDist
	 * @see com.foxinmy.weixin4j.mp.datacube.InterfaceSummary
	 * @return 统计结果
	 * @see <a
	 *      href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141082&token=&lang=zh_CN">用户分�?</a>
	 * @see <a
	 *      href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分�?</a>
	 * @see <a
	 *      href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消�?�分�?</a>
	 * @see <a
	 *      href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141086&token=&lang=zh_CN">接�?�分�?</a>
	 * @throws WeixinException
	 */
	public List<?> datacube(DatacubeType datacubeType, Date beginDate,
			Date endDate) throws WeixinException {
		String datacube_uri = getRequestUri("datacube_uri");
		Token token = tokenManager.getCache();
		JSONObject obj = new JSONObject();
		obj.put("begin_date", DateUtil.fortmat2yyyy_MM_dd(beginDate));
		obj.put("end_date", DateUtil.fortmat2yyyy_MM_dd(endDate));
		WeixinResponse response = weixinExecutor.post(String.format(datacube_uri,
				datacubeType.name().toLowerCase(), token.getAccessToken()), obj
				.toJSONString());

		return JSON.parseArray(response.getAsJson().getString("list"),
				datacubeType.getClazz());
	}
}

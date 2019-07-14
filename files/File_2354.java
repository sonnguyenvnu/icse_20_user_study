package com.zheng.common.util;


import javax.servlet.http.HttpServletRequest;

/**
 * 分页实体类
 * @author shuzheng
 * @date 2016年7月6日 下�?�6:05:00
 */
public class Paginator {

	// 总记录数
	private long total = 0L;
	// 当�?页数
	private int page = 1;
	// 总页数
	private long totalPage = 1;
	// �?页记录数
	private int rows = 10;
	// 最多显示分页页�?数
	private int step = 5;
	// 分页�?�数�??称，用于支�?一个页�?�多个分页功能
	private String param = "page";
	// 项目路径
	private String url = "";
	// 当�?页所有�?�数
	private String query = "";

	public Paginator() {

	}

	public Paginator(long total, int page, int rows, HttpServletRequest request) {
		setTotal(total);
		setPage(page);
		setRows(rows);
		setUrl(request.getRequestURI());
		setQuery(request.getQueryString());
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
		this.initTotalPage();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		// 设置个最大记录数，�?制�?�页记录过多
		if (rows > 10000) {
			rows = 10000;
		}
		this.rows = rows;
		this.initTotalPage();
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	/**
	 * �?始化分页信�?�
	 */
	public void initTotalPage() {
		totalPage = (total % rows) == 0 ? (total / rows) : ((total / rows) + 1);
		if (page > totalPage) {
			page = (int) totalPage;
		}
		if (page < 1) {
			page = 1;
		}
	}

	/**
	 * 生�?简�?�的分页页�?�内容
	 * @return
	 */
	public String getHtml() {
		// 根�?�request获�?�当�?url，包括�?�数，如果有已存在�??称未paramname的�?�数，剔除掉，�?��?�会追加新的�?�数
		//String contextPath = request.getContextPath();
		//String requestURI = request.getRequestURI();
		//String url = contextPath + requestURI;
		//String url = request.getRequestURI();
		//String query = request.getQueryString();
		if (query != null) {
			String params = "";
			String[] querys = query.split("&");
			for (int i = 0; i < querys.length; i++) {
				if (querys[i].startsWith(param)) {
					continue;
				}
				if ("".equals(params)) {
					params = querys[i];
				} else {
					params += "&" + querys[i];
				}
			}
			if (!"".equals(params)) {
				url += "?" + params;
			}
		}
		// 结果html
		String pages = "";

		int pageCount = (int) Math.ceil((double) total / rows);// 求总页数
		if (pageCount <= 1) {
			return pages;
		}
		if (page > pageCount) {
			page = pageCount;// 如果分页�?��?大总页数，则将分页�?��?设计为总页数
		}
		if (page <= 0) {
			page = 1;// 如果分页�?��?�?于１,则将分页�?��?设为１
		}

		// 显示上一页
		if (page > 1) {
			if (url.contains("?")) {
				pages = pages.concat("<a class=\"prev\" href=\"" + url + "&" + param + "=" + (page - 1) + "\">上一页</a>\n");
			} else {
				pages = pages.concat("<a class=\"prev\" href=\"" + url + "?" + param + "=" + (page - 1) + "\">上一页</a>\n");
			}
		} else {
			// 特定需求�?��?�?
			pages = pages.concat("<a class=\"prev\" href=\"javascript:;\" style=\"color:#ccc\">上一页</a>\n");
		}
		// 如果总页数大于�?显示的个数，则拼接显示
		if (pageCount > step) {
			// 显示分页�?
			int listBegin = (page - (int) Math.floor((double) step / 2));// 从第几页开始显示分页信�?�
			if (listBegin < 1) {
				listBegin = 1;
			}
			// 显示第1页
			if (listBegin >= 2) {
				if (url.contains("?")) {
					pages = pages.concat("<a href=\"" + url + "&" + param + "=1\">1</a> ... \n");
				} else {
					pages = pages.concat("<a href=\"" + url + "?" + param + "=1\">1</a> ... \n");
				}
			}
			// 当�?页数�?�侧还有未显示页�?时
			if (pageCount - page >= page - listBegin) {
				for (int i = listBegin; i < (listBegin + step); i++) {
					if (i != page) {
						if (url.contains("?")) {
							pages = pages.concat("<a href=\"" + url + "&" + param + "=" + i + "\">" + i + "</a>\n");
						} else {
							pages = pages.concat("<a href=\"" + url + "?" + param + "=" + i + "\">" + i + "</a>\n");
						}
					} else {
						pages = pages.concat("<span class=\"current\">" + i + "</span>\n");
					}
				}
				// 显示最�?�1页
				if (listBegin + step <= pageCount) {
					if (url.contains("?")) {
						pages = pages.concat(" ... <a href=\"" + url + "&" + param + "=" + pageCount + "\">" + pageCount + "</a>\n");
					} else {
						pages = pages.concat(" ... <a href=\"" + url + "?" + param + "=" + pageCount + "\">" + pageCount + "</a>\n");
					}
				}
			} else { // 显示最�?�剩余的几个页�?
				for (int i = (pageCount - step) + 1; i <= pageCount; i++) {
					if (i != page) {
						if (url.contains("?")) {
							pages = pages.concat("<a href=\"" + url + "&" + param + "=" + i + "\">" + i + "</a>\n");
						} else {
							pages = pages.concat("<a href=\"" + url + "?" + param + "=" + i + "\">" + i + "</a>\n");
						}
					} else {
						pages = pages.concat("<span class=\"current\">" + i + "</span>\n");
					}
				}
			}
		} else { // 总页数�?于等于step时，直接显示
			for (int i = 1; i <= pageCount; i++) {
				if (i != page) {
					if (url.contains("?")) {
						pages = pages.concat("<a href=\"" + url + "&" + param + "=" + i + "\">" + i + "</a>\n");
					} else {
						pages = pages.concat("<a href=\"" + url + "?" + param + "=" + i + "\">" + i + "</a>\n");
					}
				} else {
					pages = pages.concat("<span class=\"current\">" + i + "</span>\n");
				}
			}
		}
		// 显示下一页
		if (page < pageCount) {
			if (url.contains("?")) {
				pages = pages.concat("<a class=\"next\" href=\"" + url + "&" + param + "=" + (page + 1) + "\">下一页</a>\n");
			} else {
				pages = pages.concat("<a class=\"next\" href=\"" + url + "?" + param + "=" + (page + 1) + "\">下一页</a>\n");
			}
		} else {
			// 特定需求�?��?�?
			pages = pages.concat("<a class=\"next\" href=\"javascript:;\" style=\"color:#ccc\">下一页</a>\n");
		}
		return pages;
	}

}

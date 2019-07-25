package org.jeecgframework.core.common.hibernate.qbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.datatable.DataTables;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.datatable.SortInfo;

/**
 * 
 *类�??述：CriteriaQuery类是对hibernate QBC查询方法的�?装，需�?的�?�数是当�?�?作的实体类
 *张代浩
 *@date： 日期：2012-12-7 时间：上�?�10:22:15
 *@version 1.0
 */
public class CriteriaQuery {
	public CriteriaQuery() {

	}

	private static final long serialVersionUID = 1L;
	private int curPage = 1;// 当�?页
	private int pageSize = 10;// 默认一页�?�数
	private String myAction;// 请求的action 地�?�
	private String myForm;// form �??字
	private CriterionList criterionList=new CriterionList();//自定义查询�?�件集�?�
	private CriterionList jqcriterionList=new CriterionList();//jquery datatable控件生�?查询�?�件集�?�
	private int isUseimage = 0;// 翻页工具�?�样�?
	private DetachedCriteria detachedCriteria;
	private Map<String, Object> map;
	private Map<String, Object> ordermap;//排�?字段
	private boolean flag = true;// 对�?�一字段进行第二次�?命�??查询时值设置FASLE�?�?存�?命�??查询�?�件
	private String field="";//查询需�?显示的字段
	private Class<?> entityClass;//POJO
	private List<?> results;// 结果集
	private int total;
	private List<String> alias = new ArrayList<String>();//�?存创建的aliasName 防止�?�?创建
	private DataGrid dataGrid;
	private DataTables dataTables;
	
	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public DataTables getDataTables() {
		return dataTables;
	}

	public void setDataTables(DataTables dataTables) {
		this.dataTables = dataTables;
	}

	public DataGrid getDataGrid() {
		return dataGrid;
	}

	public void setDataGrid(DataGrid dataGrid) {
		this.dataGrid = dataGrid;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
	public CriterionList getJqcriterionList() {
		return jqcriterionList;
	}

	public void setJqcriterionList(CriterionList jqcriterionList) {
		this.jqcriterionList = jqcriterionList;
	}

	public CriteriaQuery(Class<?> c) {
		this.detachedCriteria = DetachedCriteria.forClass(c);
		this.map = new HashMap<String, Object>();

		this.ordermap = new LinkedHashMap<String, Object>();

	}

	public CriteriaQuery(Class<?> c, int curPage, String myAction, String myForm) {
		this.curPage = curPage;
		this.myAction = myAction;
		this.myForm = myForm;
		this.detachedCriteria = DetachedCriteria.forClass(c);
	}

	public CriteriaQuery(Class<?> c, int curPage, String myAction) {
		this.myAction = myAction;
		this.curPage = curPage;
		this.detachedCriteria = DetachedCriteria.forClass(c);
		this.map = new HashMap<String, Object>();

		this.ordermap = new LinkedHashMap<String, Object>();

	}

	public CriteriaQuery(Class<?> entityClass, int curPage) {
		this.curPage = curPage;
		this.detachedCriteria = DetachedCriteria.forClass(entityClass);
		this.map = new HashMap<String, Object>();
	}
	public CriteriaQuery(Class<?> entityClass,DataGrid dg) {
		this.curPage = dg.getPage();
		//String[] fieldstring=dg.getField().split(",");
		//this.detachedCriteria = DetachedCriteriaUtil
		//.createDetachedCriteria(c, "start", "_table",fieldstring);
		this.detachedCriteria = DetachedCriteria.forClass(entityClass);
		//Criteria criteria = null;

		this.field=dg.getField();
		this.entityClass=entityClass;
		this.dataGrid=dg;
		this.pageSize=dg.getRows();
		this.map = new HashMap<String, Object>();

		this.ordermap = new LinkedHashMap<String, Object>();

	}
	
//	 �?scott 20180526 删除无用代�?|xwork-core】
	public CriteriaQuery(Class entityClass,DataTables dataTables) {
		this.curPage = dataTables.getDisplayStart();
		String[] fieldstring=dataTables.getsColumns().split(",");

		this.detachedCriteria = DetachedCriteria.forClass(entityClass);
		//this.detachedCriteria = DetachedCriteriaUtil.createDetachedCriteria(entityClass, "start", "_table",fieldstring);

		
		this.field=dataTables.getsColumns();
		this.entityClass=entityClass;
		this.dataTables=dataTables;
		this.pageSize=dataTables.getDisplayLength();
		this.map = new HashMap<String, Object>();

		this.ordermap = new LinkedHashMap<String, Object>();

		addJqCriteria(dataTables);
	}

	public CriteriaQuery(Class c, int pageSize, int curPage,
			String myAction, String myForm) {
		this.pageSize = pageSize;
		this.curPage = curPage;
		this.myAction = myAction;
		this.myForm = myForm;
		this.detachedCriteria = DetachedCriteria.forClass(c);
	}

	/**
	 * 加载�?�件(�?�件之间有关�?�) hql((this_.0 like ? and this_.1 like ?) or this_.2 like ?)
	 * 表示法cq.add(cq.or(cq.and(cq, 0, 1), cq, 2))----- hql2:(this_.0 like ? or
	 * this_.1 like ?) 表示法:cq.add(cq.or(cq, 0, 1));
	 * 例�?：cq.in("TBPrjstatus.code", status);
		cq.eq("attn", user.getUserName());
		cq.isNull("attn");
		cq.add(cq.and(cq.or(cq, 1, 2), cq, 0));
	 */
	public void add(Criterion c) {
		detachedCriteria.add(c);
	}

	/**
	 * 加载�?�件
	 */
	public void add() {
		for (int i = 0; i < getCriterionList().size(); i++) {
			add(getCriterionList().getParas(i));
		}
		getCriterionList().removeAll(getCriterionList());
	}
	/**
	 * 加载dataTables 默认查询�?�件
	 * @param dataTables
	 */
	public void addJqCriteria(DataTables dataTables) {
		String search=dataTables.getSearch();//查询关键字
		SortInfo[] sortInfo=dataTables.getSortColumns();//排�?字段
		String[] sColumns=dataTables.getsColumns().split(",");//字段
		if(StringUtil.isNotEmpty(search))
		{
			for (String string : sColumns) {
				if(string.indexOf("_")==-1)
				{
					jqcriterionList.addPara(Restrictions.like(string, "%" + search
							+ "%"));
				}
			}
			add(getOrCriterion(jqcriterionList));
			
		}
		if(sortInfo.length>0)
		{
			for (SortInfo sortInfo2 : sortInfo) {
				addOrder(""+sColumns[sortInfo2.getColumnId()]+"",sortInfo2.getSortOrder());
			}
		}
	}

	public void createCriteria(String name) {
		detachedCriteria.createCriteria(name);
	}

	public void createCriteria(String name, String value) {
		detachedCriteria.createCriteria(name, value);
	}

	/**
	 * 创建外键表关�?�对象
	 * 
	 * @param name外键表实体�??
	 * @param value引用�??
	 */
	public void createAlias(String name, String value) {
		if(!alias.contains(name)){
			detachedCriteria.createAlias(name, value);
			alias.add(name);
		}
	}

	public void setResultTransformer(Class<?> class1) {
		detachedCriteria.setResultTransformer(Transformers.aliasToBean(class1));
	}

	public void setProjection(Property property) {
		detachedCriteria.setProjection(property);
	}

	/**
	 * 设置�?�件之间and关系
	 * 
	 * @param query
	 * @param source
	 * @param dest
	 *            hql((this_.0 like ? and this_.1 like ?) or this_.2 like ?)
	 *            表示法cq.add(cq.or(cq.and(cq, 0, 1), cq, 2))
	 * @return
	 */
	public Criterion and(CriteriaQuery query, int source, int dest) {
		return Restrictions.and(query.getCriterionList().getParas(source),
				query.getCriterionList().getParas(dest));
	}

	/**
	 * 设置�?�件之间and关系
	 * 
	 * @param query
	 * @param source
	 * @param dest
	 *            hql:(this_.0 like ? or this_.1 like ?) 表示法:cq.add(cq.or(cq, 0,
	 *            1));
	 * @return
	 */
	public Criterion and(Criterion c, CriteriaQuery query, int souce) {
		return Restrictions.and(c, query.getCriterionList().getParas(souce));
	}
	
	/**
	 *根�?�CriterionList组�?�嵌套�?�件
	 */
	public Criterion getOrCriterion(CriterionList list) {
		Criterion c1=null;
		Criterion c2=null;
		Criterion c3=null;
		c1=list.getParas(0);
		for (int i = 1; i < list.size(); i++) {
			c2=list.getParas(i);
			c3=getor(c1, c2);
			c1=c3;
		}
		return c3;
	}
	/**
	 * 设置组�?��?�的Criterion OR关系
	 * 
	 * @param query
	 * @param source
	 * @param dest
	 * @return
	 */
	public Criterion getor(Criterion c1,Criterion c2) {
		return Restrictions.or(c1, c2);
	}
	

	/**
	 * 设置�?�件之间and关系
	 * 
	 * @param query
	 * @param source
	 * @param dest
	 * @return
	 */
	public Criterion and(Criterion c1, Criterion c2)

	{
		return Restrictions.and(c1, c2);
	}

	/**
	 * 设置Or查询
	 * 
	 * @param query
	 * @param source�?�件1
	 * @param dest�?�件2
	 * @return
	 */
	public Criterion or(CriteriaQuery query, int source, int dest) {
		return Restrictions.or(query.getCriterionList().getParas(source), query
				.getCriterionList().getParas(dest));
	}

	/**
	 * 设置or(Criterion c, CriteriaQuery query, int source)（或）查询�?�件
	 * 
	 * @param keyname
	 * @param keyvalue1
	 * @param keyvalue2
	 */
	public Criterion or(Criterion c, CriteriaQuery query, int source) {
		return Restrictions.or(c, query.getCriterionList().getParas(source));
	}

	/**
	 * 设置or(Criterion c1, Criterion c2)（或）查询�?�件
	 * 
	 * @param keyname
	 * @param keyvalue1
	 * @param keyvalue2
	 *            两个�?�件或查询： Restrictions.or(Restrictions.in("username",list1),
	 *            Restrictions.idEq(1)); 三个或多个�?�件查询:（使用嵌套方�?）
	 *            criteria.add(Restrictions
	 *            .or(Restrictions.in("username",list1),
	 *            Restrictions.or(Restrictions.idEq(3), Restrictions.idEq(4))));
	 */
	public void or(Criterion c1, Criterion c2) {
		this.detachedCriteria.add(Restrictions.or(c1, c2));
	}

	/**
	 * 设置order（排�?）查询�?�件
	 * 
	 * @param ordername
	 *            ：排�?字段�??
	 * @param ordervalue
	 *            ：排�?字段值（"asc","desc"）
	 */
	public void addOrder(String ordername, SortDirection ordervalue) {
		ordermap.put(ordername,ordervalue);

	}
	/**
	 * 设置order（排�?）查询�?�件
	 * 
	 * @param ordername
	 *            ：排�?字段�??
	 * @param ordervalue
	 *            ：排�?字段值（"asc","desc"）
	 */
	public void setOrder(Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			judgecreateAlias(entry.getKey());
			if (SortDirection.asc.equals(entry.getValue())) {
				detachedCriteria.addOrder(Order.asc(entry.getKey()));
			} else {
				detachedCriteria.addOrder(Order.desc(entry.getKey()));
			}
		}
	}
	
	/**
	 * 创建 alias 
	 * @param entitys
	 * 规则 entitys 为a.b.c 这�?将会创建 alias a和alias  b而�?会创建c
	 * 因为这样更加容易传值
	 */
	public void judgecreateAlias(String entitys) {
		String[] aliass = entitys.split("\\.");
		for (int i = 0 ;i<aliass.length-1;i++){
			createAlias(aliass[i], aliass[i]);
		}
	}

	public Map<String, Object> getOrdermap() {
		return ordermap;
	}

	public void setOrdermap(Map<String, Object> ordermap) {
		this.ordermap = ordermap;
	}

	/**
	 * 设置eq(相等)查询�?�件
	 * 
	 * @param keyname
	 *            :字段�??
	 * @param keyvalue
	 *            ：字段值
	 */
	public void eq(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			criterionList.addPara(Restrictions.eq(keyname, keyvalue));
			if (flag) {
				this.put(keyname, keyvalue);
			}
			flag = true;
		}
	}

	/**
	 * 设置notEq(�?等)查询�?�件
	 * 
	 * @param keyname
	 * @param keyvalue1
	 * @param keyvalue2
	 */
	public void notEq(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			criterionList.addPara(Restrictions.ne(keyname, keyvalue));
			if (flag) {
				this.put(keyname, keyvalue);
			}
			flag = true;
		}
	}

	/**
	 * 设置like(模糊)查询�?�件
	 * 
	 * @param keyname
	 * @param keyvalue1
	 * @param keyvalue2
	 */
	public void like(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			//criterionList.addPara(Restrictions.like(keyname, "%" + keyvalue+ "%"));
			criterionList.addPara(Restrictions.like(keyname, keyvalue));
			if (flag) {
				this.put(keyname, keyvalue);
			}
			flag = true;
		}
	}

	/**
	 * 设置gt(>)查询�?�件
	 * 
	 * @param keyname
	 * @param keyvalue1
	 * @param keyvalue2
	 */
	public void gt(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			criterionList.addPara(Restrictions.gt(keyname, keyvalue));
			if (flag) {
				this.put(keyname, keyvalue);
			}
			flag = true;
		}
	}

	/**
	 * 设置lt(<)查询�?�件
	 * 
	 * @param keyname
	 * @param keyvalue1
	 * @param keyvalue2
	 */
	public void lt(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			criterionList.addPara(Restrictions.lt(keyname, keyvalue));
			if (flag) {
				this.put(keyname, keyvalue);
			}
			flag = true;
		}
	}

	/**
	 * 设置le(<=)查询�?�件
	 * 
	 * @param keyname
	 * @param keyvalue1
	 * @param keyvalue2
	 */
	public void le(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			criterionList.addPara(Restrictions.le(keyname, keyvalue));
			if (flag) {
				this.put(keyname, keyvalue);
			}
			flag = true;
		}
	}

	/**
	 * 设置ge(>=)查询�?�件
	 * 
	 * @param keyname
	 * @param keyvalue1
	 * @param keyvalue2
	 */
	public void ge(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			criterionList.addPara(Restrictions.ge(keyname, keyvalue));
			if (flag) {
				this.put(keyname, keyvalue);
			}
			flag = true;
		}
	}

	/**
	 * 设置in(包�?�)查询�?�件
	 * 
	 * @param keyname
	 * @param keyvalue1
	 * @param keyvalue2
	 */
	public void in(String keyname, Object[] keyvalue) {
		if (keyvalue != null&&keyvalue.length>0&& keyvalue[0] != "") {
			criterionList.addPara(Restrictions.in(keyname, keyvalue));
		}
	}

	/**
	 * 设置isNull查询�?�件
	 * 
	 * @param keyname
	 * @param keyvalue1
	 * @param keyvalue2
	 */
	public void isNull(String keyname) {
		criterionList.addPara(Restrictions.isNull(keyname));
	}

	/**
	 * 设置isNull查询�?�件
	 * 
	 * @param keyname
	 * @param keyvalue1
	 * @param keyvalue2
	 */
	public void isNotNull(String keyname) {
		criterionList.addPara(Restrictions.isNotNull(keyname));
	}

	/**
	 * �?存查询�?�件
	 * 
	 * @param keyname
	 * @param keyvalue1
	 * @param keyvalue2
	 */
	public void put(String keyname, Object keyvalue) {
		if (keyvalue != null && keyvalue != "") {
			map.put(keyname, keyvalue);
		}
	}

	/**
	 * 设置between(之间)查询�?�件
	 * 
	 * @param keyname
	 * @param keyvalue1
	 * @param keyvalue2
	 */
	public void between(String keyname, Object keyvalue1, Object keyvalue2) {
		Criterion c = null;// 写入between查询�?�件

		if (oConvertUtils.isNotEmpty(keyvalue1) && oConvertUtils.isNotEmpty(keyvalue2)) {
			c = Restrictions.between(keyname, keyvalue1, keyvalue2);
		} else if (oConvertUtils.isNotEmpty(keyvalue1)) {
			c = Restrictions.ge(keyname, keyvalue1);
		} else if (oConvertUtils.isNotEmpty(keyvalue2)) {
			c = Restrictions.le(keyname, keyvalue2);
		}
		criterionList.add(c);
	}

	public void sql(String sql) {
		Restrictions.sqlRestriction(sql);
	}

	public void sql(String sql, Object[] objects, Type[] type) {
		Restrictions.sqlRestriction(sql, objects, type);
	}

	public void sql(String sql, Object objects, Type type) {
		Restrictions.sqlRestriction(sql, objects, type);
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置分页显示数
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getMyAction() {
		return myAction;
	}

	public void setMyAction(String myAction) {
		this.myAction = myAction;
	}

	public String getMyForm() {
		return myForm;
	}

	public void setMyForm(String myForm) {
		this.myForm = myForm;
	}

	public CriterionList getCriterionList() {
		return criterionList;
	}

	public void setCriterionList(CriterionList criterionList) {
		this.criterionList = criterionList;
	}

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

	public int getIsUseimage() {
		return isUseimage;
	}

	/**
	 * 设置工具�?�样�?（0:�?带图片：1带图片）
	 * 
	 * @param isUseimage
	 */
	public void setIsUseimage(int isUseimage) {
		this.isUseimage = isUseimage;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public boolean isFlag() {
		return flag;
	}

	/**
	 * 对�?�一字段进行第二次�?命�??查询时值设置FASLE�?�?存�?命�??查询�?�件
	 * 
	 * @param flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void clear(){
		criterionList.clear();
		jqcriterionList.clear();
		alias.clear();
		if(map!=null){map.clear();}
		if(ordermap!=null){ordermap.clear();}
		entityClass=null;

		dataGrid = null;
		dataTables = null;
		detachedCriteria = null;

		criterionList = null;
		jqcriterionList = null;
		jqcriterionList = null;
		map = null;
		ordermap = null;
		alias = null;
		field = null;
	}
}

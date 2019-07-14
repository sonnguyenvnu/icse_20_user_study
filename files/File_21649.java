package org.nlpcn.es4sql.query;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import org.elasticsearch.action.search.*;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.sort.*;
import org.nlpcn.es4sql.domain.*;
import org.nlpcn.es4sql.domain.hints.Hint;
import org.nlpcn.es4sql.domain.hints.HintType;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.query.maker.QueryMaker;

/**
 * Transform SQL query to standard Elasticsearch search query
 */
public class DefaultQueryAction extends QueryAction {

	private final Select select;
	private SearchRequestBuilder request;

    private List<String> fieldNames = new LinkedList<>();

	public DefaultQueryAction(Client client, Select select) {
		super(client, select);
		this.select = select;
	}

	/**
	 * zhongshu-comment �?�被调用了一次，就在AggregationQueryAction类中
	 * @param request
	 * @throws SqlParseException
	 */
	public void intialize(SearchRequestBuilder request) throws SqlParseException {
		this.request = request;
	}

	//zhongshu-comment 将sql字符串解�?�?�的java对象，转�?�为es的查询请求对象
	@Override
	public SqlElasticSearchRequestBuilder explain() throws SqlParseException {
        Hint scrollHint = null;
        for (Hint hint : select.getHints()) {
            if (hint.getType() == HintType.USE_SCROLL) {
                scrollHint = hint;
                break;
            }
        }
        if (scrollHint != null && scrollHint.getParams()[0] instanceof String) {
            return new SqlElasticSearchRequestBuilder(new SearchScrollRequestBuilder(client, SearchScrollAction.INSTANCE, (String) scrollHint.getParams()[0]).setScroll(new TimeValue((Integer) scrollHint.getParams()[1])));
        }

		/*
		zhongshu-comment 6.1.1.5这个版本和elastic6.1.1这个分支用的是这一行代�?
		但是在本地调试时我的client没有实例化，并没有去连es，所以这行代�?会报空指针
		那就将这行注释掉�?�，以�?�就用下�?�那行
		 */
//		this.request = client.prepareSearch();

		/*
		zhongshu-comment  6.2.4.1这个版本和master_zhongshu_dev_01用的是这一行代�?，虽然client为null，但是下�?�这行代�?并�?会报空指针
							为了在本地调试�?执行下文的那些代�?获得es的dsl，所以就使用这行代�?，暂时将上�?�哪一行注释掉，上线的时候记得替�?�掉
		�?��?request是es�?�索请求对象，调用的是es的api，SearchRequestBuilder是es的原生api
		 */
        this.request = new SearchRequestBuilder(client, SearchAction.INSTANCE);
		setIndicesAndTypes();

		//zhongshu-comment 将Select对象中�?装的sql token信�?�转�?�并传到�?员�?��?es�?�索请求对象request中
		setFields(select.getFields());

		setWhere(select.getWhere());
		setSorts(select.getOrderBys());
		setLimit(select.getOffset(), select.getRowCount());

        //
        if (scrollHint != null) {
            if (!select.isOrderdSelect())
                request.addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC);
            request.setSize((Integer) scrollHint.getParams()[0]).setScroll(new TimeValue((Integer) scrollHint.getParams()[1]));
        } else {
            request.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
        }
        updateRequestWithIndexAndRoutingOptions(select, request);
		updateRequestWithHighlight(select, request);
		updateRequestWithCollapse(select, request);
		updateRequestWithPostFilter(select, request);
		updateRequestWithStats(select, request);
		updateRequestWithPreference(select, request);
		SqlElasticSearchRequestBuilder sqlElasticRequestBuilder = new SqlElasticSearchRequestBuilder(request);

		return sqlElasticRequestBuilder;
	}

	/**
	 * Set indices and types to the search request.
	 */
	private void setIndicesAndTypes() {
		request.setIndices(query.getIndexArr());

		String[] typeArr = query.getTypeArr();
		if (typeArr != null) {
			request.setTypes(typeArr);
		}
	}

	/**
	 * Set source filtering on a search request.
	 * zhongshu-comment �?�es dsl中的include和exclude
	 * @param fields
	 *            list of fields to source filter.
	 */
	public void setFields(List<Field> fields) throws SqlParseException {
		/*
		zhongshu-comment select * from tbl_a;
		select * 这�?sql语�?�的select.getFields().size()为0
		 */
		if (select.getFields().size() > 0) {
			ArrayList<String> includeFields = new ArrayList<String>();
			ArrayList<String> excludeFields = new ArrayList<String>();

			for (Field field : fields) {
				if (field instanceof MethodField) { //zhongshu-comment MethodField是Field的�?类，而且Field也就�?�有MethodField这一个�?类了
					MethodField method = (MethodField) field;
					if (method.getName().toLowerCase().equals("script")) {
						/*
						zhongshu-comment scripted_field only allows script(name,script) or script(name,lang,script)
						script类型的MethodField是�?会加到include和exclude中的
						 */
						handleScriptField(method);
					} else if (method.getName().equalsIgnoreCase("include")) {
					    String f;
						for (KVValue kvValue : method.getParams()) {
							//zhongshu-comment select a,b,c 中的a�?b�?c字段add到includeFields中
                            f = kvValue.value.toString();
                            fieldNames.add(f);
                            includeFields.add(f);
						}
					} else if (method.getName().equalsIgnoreCase("exclude")) {
						for (KVValue kvValue : method.getParams()) {
							excludeFields.add(kvValue.value.toString()) ;
						}
					}
				} else if (field != null) {
                    fieldNames.add(field.getName());
					includeFields.add(field.getName());
				}
			}

			request.setFetchSource(
					includeFields.toArray(new String[includeFields.size()]),
					excludeFields.toArray(new String[excludeFields.size()])
			);
		}
	}

	/**
	 * zhongshu-comment scripted_field only allows script(name,script) or script(name,lang,script)
	 * @param method
	 * @throws SqlParseException
	 */
	private void handleScriptField(MethodField method) throws SqlParseException {
		List<KVValue> params = method.getParams();
		if (params.size() == 2) {
            String f = params.get(0).value.toString();
            fieldNames.add(f);
            request.addScriptField(f, new Script(params.get(1).value.toString()));
        } else if (params.size() == 3) {
            String f = params.get(0).value.toString();
            fieldNames.add(f);
            request.addScriptField(f,
									new Script(
											ScriptType.INLINE,
											params.get(1).value.toString(),
											params.get(2).value.toString(),
											Collections.emptyMap()
									)
			);
		} else {
			throw new SqlParseException("scripted_field only allows script(name,script) or script(name,lang,script)");
		}
	}

	/**
	 * Create filters or queries based on the Where clause.
	 * 
	 * @param where
	 *            the 'WHERE' part of the SQL query.
	 * @throws SqlParseException
	 */
	private void setWhere(Where where) throws SqlParseException {
		if (where != null) {
			BoolQueryBuilder boolQuery = QueryMaker.explan(where,this.select.isQuery);
			request.setQuery(boolQuery);
		}
	}

	/**
	 * Add sorts to the elasticsearch query based on the 'ORDER BY' clause.
	 * 
	 * @param orderBys
	 *            list of Order object
	 */
	private void setSorts(List<Order> orderBys) {
		for (Order order : orderBys) {
            if (order.getNestedPath() != null) {
                request.addSort(SortBuilders.fieldSort(order.getName()).order(SortOrder.valueOf(order.getType())).setNestedSort(new NestedSortBuilder(order.getNestedPath())));
            } else if (order.getName().contains("script(")) { //zhongshu-comment 该分支是我�?��?�加的，用于兼容order by case when那�?情况

				String scriptStr = order.getName().substring("script(".length(), order.getName().length() - 1);
				Script script = new Script(scriptStr);
				ScriptSortBuilder scriptSortBuilder = SortBuilders.scriptSort(script, order.getScriptSortType());

				scriptSortBuilder = scriptSortBuilder.order(SortOrder.valueOf(order.getType()));
				request.addSort(scriptSortBuilder);
			} else {
                request.addSort(
                		order.getName(),
						SortOrder.valueOf(order.getType()));
            }
		}
	}

	/**
	 * Add from and size to the ES query based on the 'LIMIT' clause
	 * 
	 * @param from
	 *            starts from document at position from
	 * @param size
	 *            number of documents to return.
	 */
	private void setLimit(int from, int size) {
		request.setFrom(from);

		if (size > -1) {
			request.setSize(size);
		}
	}

	public SearchRequestBuilder getRequestBuilder() {
		return request;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }
}

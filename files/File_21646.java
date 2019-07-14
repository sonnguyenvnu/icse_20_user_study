package org.nlpcn.es4sql.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.join.aggregations.JoinAggregationBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ReverseNestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.nlpcn.es4sql.domain.Field;
import org.nlpcn.es4sql.domain.KVValue;
import org.nlpcn.es4sql.domain.MethodField;
import org.nlpcn.es4sql.domain.Order;
import org.nlpcn.es4sql.domain.Select;
import org.nlpcn.es4sql.domain.Where;
import org.nlpcn.es4sql.domain.hints.Hint;
import org.nlpcn.es4sql.domain.hints.HintType;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.query.maker.AggMaker;
import org.nlpcn.es4sql.query.maker.QueryMaker;

/**
 * Transform SQL query to Elasticsearch aggregations query
 */
public class AggregationQueryAction extends QueryAction {

    private final Select select;
    private AggMaker aggMaker = new AggMaker();
    private SearchRequestBuilder request;

    public AggregationQueryAction(Client client, Select select) {
        super(client, select);
        this.select = select;
    }

    @Override
    public SqlElasticSearchRequestBuilder explain() throws SqlParseException {
//        this.request = client.prepareSearch();//zhongshu-comment elastic6.1.1的写法
        this.request = new SearchRequestBuilder(client, SearchAction.INSTANCE); //zhongshu-comment master的写法

        setIndicesAndTypes();

        setWhere(select.getWhere()); //zhongshu-comment 和DefaultQueryAction的setWhere()一样
        AggregationBuilder lastAgg = null;
        //zhongshu-comment 因为es的aggs是�?�以多�?�线的，a线�?�能是group by �?,城市，b线�?�能是group by 性别�?年龄，所以select的groupBys字段是�?�层List，第一层是a线�?b线，第二层是�?�?�线�?group by哪些字段
        for (List<Field> groupBy : select.getGroupBys()) {
            if (!groupBy.isEmpty()) {
                Field field = groupBy.get(0);

                //zhongshu-comment 使得group by�?�以使用select�?�?�中字段的别�??
                //make groupby can reference to field alias
                lastAgg = getGroupAgg(field, select);

                /*
                zhongshu-comment �?�如limit是比200�?，那shard size就设为5000，
                                 �?�如limit是比200大，那shard size等于size的为准？
                 */
                if (lastAgg != null && lastAgg instanceof TermsAggregationBuilder && !(field instanceof MethodField)) {
                    //if limit size is too small, increasing shard  size is required
                    if (select.getRowCount() < 200) {
                        ((TermsAggregationBuilder) lastAgg).shardSize(5000);
                        for (Hint hint : select.getHints()) {
                            if (hint.getType() == HintType.SHARD_SIZE) {
                                if (hint.getParams() != null && hint.getParams().length != 0 && hint.getParams()[0] != null) {
                                    ((TermsAggregationBuilder) lastAgg).shardSize((Integer) hint.getParams()[0]);
                                }
                            }
                        }
                    }

                    setSize(lastAgg, field);
                    setShardSize(lastAgg);
                }

                if (field.isNested()) {
                    AggregationBuilder nestedBuilder = createNestedAggregation(field);

                    if (insertFilterIfExistsAfter(lastAgg, groupBy, nestedBuilder, 1)) {
                        groupBy.remove(1);
                    } else {
                        nestedBuilder.subAggregation(lastAgg);
                    }

                    request.addAggregation(wrapNestedIfNeeded(nestedBuilder, field.isReverseNested()));
                } else if (field.isChildren()) {
                    AggregationBuilder childrenBuilder = createChildrenAggregation(field);

                    if (insertFilterIfExistsAfter(lastAgg, groupBy, childrenBuilder, 1)) {
                        groupBy.remove(1);
                    } else {
                        childrenBuilder.subAggregation(lastAgg);
                    }

                    request.addAggregation(childrenBuilder);
                } else {
                    request.addAggregation(lastAgg);
                }

                //zhongshu-comment 下标从1开始
                for (int i = 1; i < groupBy.size(); i++) {
                    field = groupBy.get(i);
                    AggregationBuilder subAgg = getGroupAgg(field, select);
                      //ES5.0 termsaggregation with size = 0 not supported anymore
//                    if (subAgg instanceof TermsAggregationBuilder && !(field instanceof MethodField)) {

//                        //((TermsAggregationBuilder) subAgg).size(0);
//                    }
                    setSize(subAgg, field);
                    setShardSize(subAgg);
                    if (field.isNested()) {
                        AggregationBuilder nestedBuilder = createNestedAggregation(field);

                        if (insertFilterIfExistsAfter(subAgg, groupBy, nestedBuilder, i + 1)) {
                            groupBy.remove(i + 1);
                            i++;
                        } else {
                            nestedBuilder.subAggregation(subAgg);
                        }

                        lastAgg.subAggregation(wrapNestedIfNeeded(nestedBuilder, field.isReverseNested()));
                    } else if (field.isChildren()) {
                        AggregationBuilder childrenBuilder = createChildrenAggregation(field);

                        if (insertFilterIfExistsAfter(subAgg, groupBy, childrenBuilder, i + 1)) {
                            groupBy.remove(i + 1);
                            i++;
                        } else {
                            childrenBuilder.subAggregation(subAgg);
                        }

                        lastAgg.subAggregation(childrenBuilder);
                    } else {
                        lastAgg.subAggregation(subAgg);
                    }

                    //zhongshu-comment 令lastAgg指�?�subAgg对象，然�?�继续下一个循环，就能达到这样的效果：a aggs下包�?�b aggs，b aggs下包�?�c aggs，c aggs下包�?�d aggs
                    lastAgg = subAgg;
                }//zhongshu-comment �?��?�线的aggs循环结�?�
            }

            // add aggregation function to each groupBy zhongshu-comment each groupBy�?�多�?�线的aggs
            /*
            zhongshu-comment �?�?�的解�?都是针对group by�?�?�中的那些字段，但group by�?�?�中的那些字段并没有指明�?统计什么指标啊，到底是count？sum？还是avg呢？
                             到底�?统计什么指标是在select�?�?�中指明的。
            例如：select c,d,sum(a),count(b) from tbl group by c,d;
            上�?�的逻辑就是解�?group by字段中的c和d，接下�?�的 explanFields() 就是解�?sum(a)和count(b)了
             */
            explanFields(request, select.getFields(), lastAgg);

        }//zhongshu-comment 多�?�线的aggs循环结�?�

        if (select.getGroupBys().size() < 1) {
            //add aggregation when having no groupBy script
            /*
            zhongshu-comment �?�如sql中没有group by�?�?�，但是别的情况有�?�能会触�?�aggs的，例如sql：
            select sum(a),count(b) from tbl;
            这�?情况就是�?�有一个组，所有数�?�就是一个组，�?分组�?��?��?�，所以还是会用到aggs的
             */
            explanFields(request, select.getFields(), lastAgg);

        }

        Map<String, KVValue> groupMap = aggMaker.getGroupMap();
        // add field
        if (select.getFields().size() > 0) {
            setFields(select.getFields());
//            explanFields(request, select.getFields(), lastAgg);
        }

        // add order
        if (lastAgg != null && select.getOrderBys().size() > 0) {
            for (Order order : select.getOrderBys()) {
                KVValue temp = groupMap.get(order.getName());
                if (temp != null) {
                    TermsAggregationBuilder termsBuilder = (TermsAggregationBuilder) temp.value;
                    switch (temp.key) {
                        case "COUNT":
                        	String orderName = order.getName();
                            if (isAliasFiled(orderName)) {
                                termsBuilder.order(BucketOrder.aggregation(orderName, isASC(order)));
                            } else {
                                termsBuilder.order(BucketOrder.count(isASC(order)));
                            }
                            break;
                        case "KEY":
                            termsBuilder.order(BucketOrder.key(isASC(order)));
                            // add the sort to the request also so the results get sorted as well
                            request.addSort(order.getName(), SortOrder.valueOf(order.getType()));
                            break;
                        case "FIELD":
                            termsBuilder.order(BucketOrder.aggregation(order.getName(), isASC(order)));
                            break;
                        default:
                            throw new SqlParseException(order.getName() + " can not to order");
                    }
                } else {
                    request.addSort(order.getName(), SortOrder.valueOf(order.getType()));
                }
            }
        }
        //zhongshu-comment 这个�?看一下
        setLimitFromHint(this.select.getHints());

        request.setSearchType(SearchType.DEFAULT);
        updateRequestWithIndexAndRoutingOptions(select, request);
        updateRequestWithHighlight(select, request);
        updateRequestWithCollapse(select, request);
        updateRequestWithPostFilter(select, request);
        updateRequestWithStats(select, request);
        updateRequestWithPreference(select, request);
        SqlElasticSearchRequestBuilder sqlElasticRequestBuilder = new SqlElasticSearchRequestBuilder(request);
        return sqlElasticRequestBuilder;
    }

    private void setSize (AggregationBuilder agg, Field field) {
        if (field instanceof MethodField) { //zhongshu-comment MethodField�?�以自定义�?��?�的size
            MethodField mf = ((MethodField) field);
            Object customSize = mf.getParamsAsMap().get("size");
            if (customSize == null) { //zhongshu-comment �?�如用户没有在MethodField指定agg的size，就将默认的rowCount设置为agg的size
                if(select.getRowCount()>0) {
                    if (agg instanceof TermsAggregationBuilder) {
                        ((TermsAggregationBuilder) agg).size(select.getRowCount());
                    }
                }
            } else {
                //zhongshu-comment �?需�?任何�?作，因为之�?步骤的代�?已�?将自定义的size设置到agg对象中了
            }
        } else {
            if(select.getRowCount()>0) {
                if (agg instanceof TermsAggregationBuilder) {
                    ((TermsAggregationBuilder) agg).size(select.getRowCount());
                }
            }
        }
    }

    private void setShardSize(AggregationBuilder agg) {
        if (agg instanceof TermsAggregationBuilder) {
            int defaultShardSize = 20 * select.getRowCount();
            ((TermsAggregationBuilder) agg).shardSize(Math.max(defaultShardSize, 5000));
        }
    }

    private AggregationBuilder getGroupAgg(Field field, Select select2) throws SqlParseException {
        boolean refrence = false;
        AggregationBuilder lastAgg = null;
        for (Field temp : select.getFields()) {
            if (temp instanceof MethodField && temp.getName().equals("script")) {
                MethodField scriptField = (MethodField) temp;
                for (KVValue kv : scriptField.getParams()) {
                    if (kv.value.equals(field.getName())) {
                        lastAgg = aggMaker.makeGroupAgg(scriptField);
                        refrence = true;
                        break;
                    }
                }
            }
        }

        /*
        zhongshu-comment reference的�?�?是引用，在该代�?上下文的�?�?是group by中使用了select�?�?�中字段的别�??
        refrence为false，就代表没有引用了别�??，就是一般的Field�?一般的group by而已，和我平常写的一样
        "aggs":{
            "city_agg":{
                "field":"city"
             }
         }
         */
        if (!refrence)
            lastAgg = aggMaker.makeGroupAgg(field);
        
        return lastAgg;
    }

    private AggregationBuilder wrapNestedIfNeeded(AggregationBuilder nestedBuilder, boolean reverseNested) {
        if (!reverseNested) return nestedBuilder;
        if (reverseNested && !(nestedBuilder instanceof NestedAggregationBuilder)) return nestedBuilder;
        //we need to jump back to root
        return AggregationBuilders.reverseNested(nestedBuilder.getName() + "_REVERSED").subAggregation(nestedBuilder);
    }

    private AggregationBuilder createNestedAggregation(Field field) {
        AggregationBuilder nestedBuilder;

        String nestedPath = field.getNestedPath();

        if (field.isReverseNested()) {
            if (nestedPath == null || !nestedPath.startsWith("~")) {
                ReverseNestedAggregationBuilder reverseNestedAggregationBuilder = AggregationBuilders.reverseNested(getNestedAggName(field));
                if(nestedPath!=null){
                    reverseNestedAggregationBuilder.path(nestedPath);
                }
                return reverseNestedAggregationBuilder;
            }
            nestedPath = nestedPath.substring(1);
        }

        nestedBuilder = AggregationBuilders.nested(getNestedAggName(field),nestedPath);

        return nestedBuilder;
    }

    private AggregationBuilder createChildrenAggregation(Field field) {
        AggregationBuilder childrenBuilder;

        String childType = field.getChildType();

        childrenBuilder = JoinAggregationBuilders.children(getChildrenAggName(field),childType);

        return childrenBuilder;
    }

    private String getNestedAggName(Field field) {
        String prefix;

        if (field instanceof MethodField) {
            String nestedPath = field.getNestedPath();
            if (nestedPath != null) {
                prefix = nestedPath;
            } else {
                prefix = field.getAlias();
            }
        } else {
            prefix = field.getName();
        }
        return prefix + "@NESTED";
    }

    private String getChildrenAggName(Field field) {
        String prefix;

        if (field instanceof MethodField) {
            String childType = field.getChildType();

            if (childType != null) {
                prefix = childType;
            } else {
                prefix = field.getAlias();
            }
        } else {
            prefix = field.getName();
        }

        return prefix + "@CHILDREN";
    }

    private boolean insertFilterIfExistsAfter(AggregationBuilder agg, List<Field> groupBy, AggregationBuilder builder, int nextPosition) throws SqlParseException {
        if (groupBy.size() <= nextPosition) return false;
        Field filterFieldCandidate = groupBy.get(nextPosition);
        if (!(filterFieldCandidate instanceof MethodField)) return false;
        MethodField methodField = (MethodField) filterFieldCandidate;
        if (!methodField.getName().toLowerCase().equals("filter")) return false;
        builder.subAggregation(aggMaker.makeGroupAgg(filterFieldCandidate).subAggregation(agg));
        return true;
    }

    private AggregationBuilder updateAggIfNested(AggregationBuilder lastAgg, Field field) {
        if (field.isNested()) {
            lastAgg = AggregationBuilders.nested(field.getName() + "Nested",field.getNestedPath())
                    .subAggregation(lastAgg);
        }
        return lastAgg;
    }

    private boolean isASC(Order order) {
        return "ASC".equals(order.getType());
    }

    private void setFields(List<Field> fields) {
        if (select.getFields().size() > 0) {
            ArrayList<String> includeFields = new ArrayList<>();

            for (Field field : fields) {
                if (field != null) {
                    includeFields.add(field.getName());
                }
            }

            request.setFetchSource(includeFields.toArray(new String[includeFields.size()]), null);
        }
    }

    private void explanFields(SearchRequestBuilder request, List<Field> fields, AggregationBuilder groupByAgg) throws SqlParseException {
        for (Field field : fields) {

            if (field instanceof MethodField) {

                if (field.getName().equals("script")) {
                    //question addStoredField()是什么鬼？
                    request.addStoredField(field.getAlias());

                    /*
                    zhongshu-comment 将request传进去defaultQueryAction对象是为了调用setFields()中的这一行代�?：request.setFetchSource(),
                                     给request设置include字段和exclude字段
                     */
                    DefaultQueryAction defaultQueryAction = new DefaultQueryAction(client, select);
                    defaultQueryAction.intialize(request);
                    List<Field> tempFields = Lists.newArrayList(field);
                    defaultQueryAction.setFields(tempFields);

                    /*
                     zhongshu-comment 因为field.getName().equals("script")的那些字段一般都是作为维度而�?是统计指标�?度�?metric，
                                        所以就�?continue，�?能继续下边的创建agg
                    */
                    continue;
                }

                AggregationBuilder makeAgg = aggMaker.makeFieldAgg((MethodField) field, groupByAgg);
                if (groupByAgg != null) {
                    groupByAgg.subAggregation(makeAgg);
                } else {
                    //question �?懂为什么将一个null的agg加到request中，这应该是dsl语法问题，先�?需�?深究
                    request.addAggregation(makeAgg);
                }
            } else if (field instanceof Field) {

                //question 为什么Filed类型的字段�?需�?�?MethodField类型字段一样设置include�?exclude字段：request.setFetchSource()
                request.addStoredField(field.getName());
            } else {
                throw new SqlParseException("it did not support this field method " + field);
            }
        }
    }

    /**
     * Create filters based on
     * the Where clause.
     *
     * @param where the 'WHERE' part of the SQL query.
     * @throws SqlParseException
     */
    private void setWhere(Where where) throws SqlParseException {
        if (where != null) {
            QueryBuilder whereQuery = QueryMaker.explan(where,this.select.isQuery);
            request.setQuery(whereQuery);
        }
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

    private void setLimitFromHint(List<Hint> hints) {
        int from = 0;
        int size = 0;
        for (Hint hint : hints) {
            if (hint.getType() == HintType.DOCS_WITH_AGGREGATION) {
                Integer[] params = (Integer[]) hint.getParams();
                if (params.length > 1) {
                    // if 2 or more are given, use the first as the from and the second as the size
                    // so it is the same as LIMIT from,size
                    // except written as /*! DOCS_WITH_AGGREGATION(from,size) */
                    from = params[0];
                    size = params[1];
                } else if (params.length == 1) {
                    // if only 1 parameter is given, use it as the size with a from of 0
                    size = params[0];
                }
                break;
            }
        }
        request.setFrom(from);
        request.setSize(size);
    }

    /**
     * 判断�?个字段�??称是�?�是别�??
     */
    private boolean isAliasFiled(String filedName) {
        if (select.getFields().size() > 0) {
            for (Field field : select.getFields()) {
                if (null != field.getAlias() && field.getAlias().equals(filedName)) {
                    return true;
                }
            }
        }
        return false;
    }

}

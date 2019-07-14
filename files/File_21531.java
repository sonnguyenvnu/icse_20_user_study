package org.elasticsearch.plugin.nlpcn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.plugin.nlpcn.executors.ActionRequestRestExecuterFactory;
import org.elasticsearch.plugin.nlpcn.executors.RestExecutor;
import org.elasticsearch.rest.*;
import org.nlpcn.es4sql.SearchDao;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.query.QueryAction;

import java.io.IOException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.*;


public class RestSqlAction extends BaseRestHandler {

    private static final Logger LOGGER = LogManager.getLogger();

	public RestSqlAction(Settings settings, RestController restController) {
        super(settings);
		restController.registerHandler(RestRequest.Method.POST, "/_sql/_explain", this);
		restController.registerHandler(RestRequest.Method.GET, "/_sql/_explain", this);
		restController.registerHandler(RestRequest.Method.POST, "/_sql", this);
		restController.registerHandler(RestRequest.Method.GET, "/_sql", this);
	}

    @Override
    public String getName() {
        return "sql_action";
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) {
        try (XContentParser parser = request.contentOrSourceParamParser()) {
            parser.mapStrings().forEach((k, v) -> request.params().putIfAbsent(k, v));
        } catch (IOException e) {
            LOGGER.warn("Please use json format params, like: {\"sql\":\"SELECT * FROM test\"}");
        }

        String sql = request.param("sql");

        if (sql == null) {
            sql = request.content().utf8ToString();
        }
        try {
            SearchDao searchDao = new SearchDao(client);
            QueryAction queryAction = null;

            queryAction = searchDao.explain(sql);//zhongshu-comment 语法解�?，将sql字符串解�?为一个Java查询对象

            // TODO add unit tests to explain. (rest level?)
            if (request.path().endsWith("/_explain")) {
                final String jsonExplanation = queryAction.explain().explain();
                return channel -> channel.sendResponse(new BytesRestResponse(RestStatus.OK, XContentType.JSON.mediaType(), jsonExplanation));
            } else {
                Map<String, String> params = request.params();

                //zhongshu-comment 生�?一个负责用rest方�?查询es的对象RestExecutor，返回的实现类是：ElasticDefaultRestExecutor
                RestExecutor restExecutor = ActionRequestRestExecuterFactory.createExecutor(params.get("format"));
                final QueryAction finalQueryAction = queryAction;
                //doing this hack because elasticsearch throws exception for un-consumed props
                Map<String, String> additionalParams = new HashMap<>();
                for (String paramName : responseParams()) {
                    if (request.hasParam(paramName)) {
                        additionalParams.put(paramName, request.param(paramName));
                    }
                }
                //zhongshu-comment restExecutor.execute()方法里会调用es查询的相关rest api
                //zhongshu-comment restExecutor.execute()方法的第1�?4个�?�数是框架传进�?�的�?�数，第2�?3个�?�数是�?�以自己生�?的�?�数，所以�?多注�?一点
                //zhongshu-comment 默认调用的是ElasticDefaultRestExecutor这个�?类
                //todo 这是什么语法：�?�索java8 -> lambda表达�?：https://blog.csdn.net/ioriogami/article/details/12782141
                return channel -> restExecutor.execute(client, additionalParams, finalQueryAction, channel);
            }
        } catch (SqlParseException | SQLFeatureNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Set<String> responseParams() {
        Set<String> responseParams = new HashSet<>(super.responseParams());
        responseParams.addAll(Arrays.asList("sql", "flat", "separator", "_score", "_type", "_id", "newLine", "format"));
        return responseParams;
    }
}

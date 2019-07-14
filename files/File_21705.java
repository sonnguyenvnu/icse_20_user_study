package org.nlpcn.es4sql.query.maker;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.common.xcontent.LoggingDeprecationHandler;
import org.elasticsearch.common.xcontent.NamedXContentRegistry;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.InnerHitBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.nlpcn.es4sql.domain.Condition;
import org.nlpcn.es4sql.domain.Where;
import org.nlpcn.es4sql.domain.Where.CONN;
import org.nlpcn.es4sql.exception.SqlParseException;

import java.io.IOException;

public class QueryMaker extends Maker {

	/**
	 * 将where�?�件构建�?query
	 * 
	 * @param where
	 * @return
	 * @throws SqlParseException
	 */
	public static BoolQueryBuilder explan(Where where) throws SqlParseException {
		return explan(where,true);
	}

    public static BoolQueryBuilder explan(Where where,boolean isQuery) throws SqlParseException {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        //zhongshu-comment 一直�?�，�?�到最深的那个where
		//zhongshu-comment 暂时�?��?�到了该sql：select a,b,c as my_c from tbl where a = 1，会走这个分支
		//就是where�?�?�中�?�有一个�?�件的情况下会走该分支
		//zhongshu-comment 那他为什么用while呢？？用if�?就得�?�？那应该是还有层层嵌套的情况，一直get到底
        while (where.getWheres().size() == 1) {
            where = where.getWheres().getFirst();
        }

        //zhongshu-comment where.getWheres().size()的长度等于0 或者 大于1
        new QueryMaker().explanWhere(boolQuery, where);

		//zhongshu-comment isQuery为true，应该就是�?计算_score的
        if(isQuery){
            return boolQuery;
        }
        //zhongshu-comment isQuery为false，应该就是使用filter，�?需�?计算_score
        return QueryBuilders.boolQuery().filter(boolQuery);
    }

	private QueryMaker() {
		super(true);
	}

	private void explanWhere(BoolQueryBuilder boolQuery, Where where) throws SqlParseException {

		//zhongshu-comment 暂时�?��?�到了该sql：select a,b,c as my_c from tbl where a = 1，会走这个分支
		if (where instanceof Condition) {
			addSubQuery(
					boolQuery,
					where,
					(QueryBuilder) make((Condition) where) //zhongshu-comment �?点方法 就是这里解�?最细粒度的where�?�件
			);
		} else {
			/*
			zhongshu-comment select a,b,c as my_c from tbl where a = 1 or b = 2 and (c = 3 or d = 4) or e > 1
			上�?�这�?�sql中的“b = 2 and (c = 3 or d = 4)�?这部分会走该分支，
			因为“b = 2 and (c = 3 or d = 4)�?被�?装为Where类型的对象，而�?是Condition对象
			对应的具体笔记�?：�?�索-->es�?�件开�?�-->es-sql-->代�?阅读-->如何解�?where�?�件
			 */
			BoolQueryBuilder subQuery = QueryBuilders.boolQuery();

			//zhongshu-comment 将subQuery对象纳入到boolQuery中，�?�boolQuery是上一级，subQuery是下一级
			addSubQuery(boolQuery, where, subQuery);
			for (Where subWhere : where.getWheres()) {
				//zhongshu-comment 然�?��?�将subWhere对象纳入到subQuery对象中，通过递归就能层层解�?出这个Where�?�件了：“b = 2 and (c = 3 or d = 4)�?
				explanWhere(subQuery, subWhere);
			}
		}
	}

	/**
	 * 增加嵌套�?�
	 * 
	 * @param boolQuery
	 * @param where
	 * @param subQuery
	 */
	private void addSubQuery(BoolQueryBuilder boolQuery, Where where, QueryBuilder subQuery) {
        if(where instanceof Condition){
            Condition condition = (Condition) where;

			if (condition.isNested()) {
				boolean isNestedQuery = subQuery instanceof NestedQueryBuilder;
				InnerHitBuilder ihb = null;
				if (condition.getInnerHits() != null) {
                    try (XContentParser parser = JsonXContent.jsonXContent.createParser(NamedXContentRegistry.EMPTY, LoggingDeprecationHandler.INSTANCE, condition.getInnerHits())) {
                        ihb = InnerHitBuilder.fromXContent(parser);
                    } catch (IOException e) {
                        throw new IllegalArgumentException("couldn't parse inner_hits: " + e.getMessage(), e);
                    }
                }

                // bugfix #628
                if ("missing".equalsIgnoreCase(String.valueOf(condition.getValue())) && (condition.getOpear() == Condition.OPEAR.IS || condition.getOpear() == Condition.OPEAR.EQ)) {
                    NestedQueryBuilder q = isNestedQuery ? (NestedQueryBuilder) subQuery : QueryBuilders.nestedQuery(condition.getNestedPath(), QueryBuilders.boolQuery().mustNot(subQuery), ScoreMode.None);
					if (ihb != null) {
						q.innerHit(ihb);
                    }
                    boolQuery.mustNot(q);
                    return;
                }

                // support not nested
                if (condition.getOpear() == Condition.OPEAR.NNESTED_COMPLEX) {
                    if (ihb != null) {
                        NestedQueryBuilder.class.cast(subQuery).innerHit(ihb);
                    }
                    boolQuery.mustNot(subQuery);
                    return;
                }

                if (!isNestedQuery) {
					subQuery = QueryBuilders.nestedQuery(condition.getNestedPath(), subQuery, ScoreMode.None);
				}
                if (ihb != null) {
                    ((NestedQueryBuilder) subQuery).innerHit(ihb);
                }
            } else if(condition.isChildren()) {
            	subQuery = JoinQueryBuilders.hasChildQuery(condition.getChildType(), subQuery, ScoreMode.None);
            }
        }

		//zhongshu-comment 将subQuery对象纳入到boolQuery中，�?�boolQuery是上一级，subQuery是下一级
		if (where.getConn() == CONN.AND) {
			boolQuery.must(subQuery);
		} else {
			boolQuery.should(subQuery);
		}
	}
}

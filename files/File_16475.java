package org.hswebframework.web.organizational.authorization.relation;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface LinkedRelations<C extends LinkedRelations> {

    /**
     * 获�?�指定方�?�以�?�维度的关系链,如: 我是�?�四的�?�?�,张三是我的�?�?�
     *
     * @param direction 关系方�?� {@link Relation.Direction}
     * @param dimension 关系维度,如: position,由实现进行自定义
     * @param relation  关系定义,如: 直属领导 {@link Relation#getRelation()}
     * @return 关系链
     * @see Relation#getRelation()
     * @see Relation#getDirection()
     */
    C relations(Relation.Direction direction, String dimension, String relation);

    /**
     * 获�?�指定方�?�的所有维度的关系链.
     * <pre>
     *     正�?�: [我]是[张三]的[研�?�部(维度)][�?�?�(关系)]
     *     �??�?�: [张三]是[我]的[研�?�部(维度)][�?�?�(关系)]
     *     �?��?�: [正�?�]或者[�??�?�]关系
     * </pre>
     *
     * @param direction 方�?�
     * @param relation  关系定义 {@link Relation#getRelation()}
     * @return 关系链
     * @see Relation#getRelation()
     * @see Relation#getDirection()
     * @see this#relations(Relation.Direction, String, String)
     */
    default C relations(Relation.Direction direction, String relation) {
        return relations(direction, null, relation);
    }

    /**
     * @see this#relations(Relation.Direction, String, String)
     * @see Relation.Direction#REVERSE
     */
    default C relationsRev(String type, String relation) {
        return relations(Relation.Direction.REVERSE, type, relation);
    }

    /**
     * @see this#relations(Relation.Direction, String)
     * @see Relation.Direction#REVERSE
     */
    default C relationsRev(String relation) {
        return relations(Relation.Direction.REVERSE, relation);
    }


    /**
     * @see this#relations(Relation.Direction, String, String)
     * @see Relation.Direction#POSITIVE
     */
    default C relationsPos(String type, String relation) {
        return relations(Relation.Direction.POSITIVE, type, relation);
    }

    /**
     * @see this#relations(Relation.Direction, String)
     * @see Relation.Direction#POSITIVE
     */
    default C relationsPos(String relation) {
        return relations(Relation.Direction.POSITIVE, relation);
    }

    /**
     * @see this#relations(Relation.Direction, String, String)
     * @see Relation.Direction#ALL
     */
    default C relations(String type, String relation) {
        return relations(Relation.Direction.ALL, type, relation);
    }

    /**
     * @see this#relations(Relation.Direction, String)
     * @see Relation.Direction#ALL
     */
    default C relations(String relation) {
        return relations(Relation.Direction.ALL, relation);
    }


    /**
     * 判断目标关系对象属性是�?�与值等于
     *
     * @param property 属性�??称,如: name
     * @param value    属性指,如: 张三
     * @return 关系链
     */
    C is(String property, Object value);

    /**
     * 判断目标关系对象属性是�?��?等于值
     *
     * @param property 属性�??称,如: name
     * @param value    属性指,如: 张三
     * @return 关系链
     */
    C not(String property, Object value);

    /**
     * 切�?�当�?过滤逻辑为或则,如:
     * <pre>
     *     //获�?�姓�??为张三或者�?�四的领导
     *     me.relations("领导").is("name","张三").or().is("name","�?�四").all();
     * </pre>
     *
     * @return 关系链
     */
    C or();

    /**
     * 切�?�当�?过滤逻辑为并且
     * <pre>
     *     //获�?�status为1并且email�?为空的领导
     *     me.relations("领导").is("status",1).and().not("email","").all();
     * </pre>
     *
     * @return 关系链
     */
    C and();

    /**
     * @return 全部关系信�?�集�?�
     */
    default List<Relation> all() {
        return stream().collect(Collectors.toList());
    }

    /**
     * 获�?�所有的关系目标标识,通常是人员的id
     *
     * @return 人员id
     */
    default List<String> allTarget() {
        return stream().map(Relation::getTarget).collect(Collectors.toList());
    }

    /**
     * @return 全部关系信�?�的stream
     */
    Stream<Relation> stream();

    C deep();
}

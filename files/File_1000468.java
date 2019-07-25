package org.nutz.dao.util.cri;

import org.nutz.dao.Nesting;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

/**
 * 逻辑基本与{@link Exps}类似,当传入Cnd.where()最�?�一个�?�件传入Nesting�?�数时会调用此类方法�?�构造sql�?�件.
 */
public class NestExps {
	public static NestingExpression eq(String name, Nesting val) {
		return new NestingExpression(name, "=", val);
	}

	public static NestingExpression notEq(String name, Nesting val) {
		return new NestingExpression(name, "<>", val);
	}

	public static NestingExpression like(String name, Nesting value) {
		return new NestingExpression(name, "LIKE", value);
	}

	public static NestingExpression inSql(String name, Nesting value) {
		return new NestingExpression(name, "IN", value);
	}

	public static NestingExpression exists(Nesting value) {
		return new NestingExpression(null, "EXISTS", value);
	}

	public static NestingExpression otherSymbol(String name, String op, Nesting value) {
		return new NestingExpression(name, op, value);
	}

	public static SqlExpression create(String name, String op, Nesting value) {
		op = Strings.trim(op.toUpperCase());
		if (value == null) {
			throw Lang.makeThrow("nesting sql can not be null'");
		} else if ("LIKE".equals(op) || "NOT LIKE".equals(op)) {
			return like(name, value).setNot(op.startsWith("NOT"));
		} else if ("=".equals(op)) {
			return eq(name, value);
		} else if ("!=".equals(op) || "<>".equals(op)) {
			return notEq(name, value);
		} else if ("IN".equals(op) || "NOT IN".equals(op)) {
			return inSql(name, value).setNot(op.startsWith("NOT"));
		} else if ("EXISTS".equals(op) || "NOT EXISTS".equals(op)) {
			// TODO op为EXITSTS的情况下,name!=null or name.length!=0 是�?�需�?报错?
			return exists(value).setNot(op.startsWith("NOT"));
		}
		return otherSymbol(name, op, value);
	}

}

package com.conditions;

import java.util.Map;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DatabaseTypeConditional implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Map<String, Object> attribs = metadata.getAnnotationAttributes(DatabaseType.class.getName());
		String type = (String) attribs.get("value");
		System.out.println("map values:");
		System.out.println(attribs);
		return System.getProperty("dbType", MySqlConditional.MYSQL_CONDITION).equalsIgnoreCase(type);
	}

}

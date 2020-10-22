package com.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MySqlConditional implements Condition {

	public static final String MYSQL_CONDITION = "MYSQL";

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return MYSQL_CONDITION.equalsIgnoreCase(System.getProperty("dbType"));
	}

}
